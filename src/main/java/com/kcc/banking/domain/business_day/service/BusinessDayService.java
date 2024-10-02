package com.kcc.banking.domain.business_day.service;

import com.kcc.banking.common.exception.ErrorCode;
import com.kcc.banking.common.exception.custom_exception.BadRequestException;
import com.kcc.banking.domain.business_day.dto.request.BusinessDayChange;
import com.kcc.banking.domain.business_day.dto.request.BusinessDayUpdate;
import com.kcc.banking.domain.business_day.dto.response.BusinessDay;
import com.kcc.banking.domain.business_day.event.BusinessDayChangeEvent;
import com.kcc.banking.domain.business_day.mapper.BusinessDayMapper;
import com.kcc.banking.domain.common.service.CommonService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class BusinessDayService {

    private final BusinessDayMapper businessDayMapper;
    private final ApplicationEventPublisher eventPublisher;
    private final CommonService commonService;

    public BusinessDay getCurrentBusinessDay() {
        return businessDayMapper.findCurrentBusinessDay();
    }

    public BusinessDay getNextBusinessDay() {
        return businessDayMapper.findNextBusinessDay();
    }

    public BusinessDay getPrevBusinessDay() {
        return businessDayMapper.findPrevBusinessDay();
    }


    /**
     * @Description
     * 1. 조회
     * 2. 현재 영업일을 마감 상태로 변경 + 다음 영업일을 오픈 및 현재 영업일로 지정
     * 3. 업데이트 내용을 객체에 반영
     *      - 요청한 다음 영업일이 서버상의 현재 영업일일 때 -> THROW ALREADY_CHANGED_BUSINESS_DAY
     *      - 요청한 다음 영업일이 마감 상태라면 -> ALREADY_CLOSED_BUSINESS_DAY
     *      - 영업일 변경을 눌렀는데 현재 영업일이 영업중일 때 -> REQUIRED_BRANCH_CLOSING
     * 4. 영업일 변경 이벤트 발행
     * 5. BusinessDayListener에서 작업을 이어받음
     * 6. 마감 데이터(행원, 매니저) 생성
     * 7. 이자생성
     */
    @Transactional(rollbackFor = Exception.class)
    //@Synchronized
    public void changeBusinessDay(BusinessDayChange businessDayChange) {


        BusinessDay currentBusinessDay = getCurrentBusinessDay();
        BusinessDay nextBusinessDay = getNextBusinessDay();
        String branchClosingStatus = commonService.getBranchClosingStatus();

        if(branchClosingStatus.equals("OPEN"))
            throw new BadRequestException(ErrorCode.REQUIRED_BRANCH_CLOSING);
        if(businessDayChange.getBusinessDateToChange().equals(currentBusinessDay.getBusinessDate().split(" ")[0]))
            throw new BadRequestException(ErrorCode.ALREADY_CHANGED_BUSINESS_DAY);



        businessDayChange(currentBusinessDay, nextBusinessDay);



        eventPublisher.publishEvent(new BusinessDayChangeEvent(businessDayChange));
    }

    // DB업데이트 및 변경사항 객체에 반영
    private void businessDayChange(BusinessDay currentBusinessDay, BusinessDay nextBusinessDay) {
        int updateResult1 = businessDayStatusToClosed(currentBusinessDay.getBusinessDate());
        int updateResult2 = businessDayStatusToOPEN(nextBusinessDay.getBusinessDate());

        if(updateResult1 == 1 && updateResult2 == 1) {
            reflectUpdateResult(currentBusinessDay, nextBusinessDay);
        }
    }

    public int businessDayStatusToClosed(String targetDate) {
        return businessDayMapper.updateStatus(BusinessDayUpdate.builder()
                .targetDate(targetDate)
                .status("CLOSED")
                .isCurrentBusinessDay("FALSE")
                .build()
        );

    }
    public int businessDayStatusToOPEN(String targetDate) {
        return businessDayMapper.updateStatus(BusinessDayUpdate.builder()
                .targetDate(targetDate)
                .status("OPEN")
                .isCurrentBusinessDay("TRUE")
                .build()
        );

    }
    private void reflectUpdateResult(BusinessDay currentBusinessDay, BusinessDay nextBusinessDay) {
        currentBusinessDay.toClose();
        nextBusinessDay.toOpen();
    }
}
