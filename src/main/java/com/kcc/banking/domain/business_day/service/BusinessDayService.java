package com.kcc.banking.domain.business_day.service;


import com.kcc.banking.common.util.AuthenticationUtils;
import com.kcc.banking.domain.business_day.dto.request.BusinessDayUpdate;
import com.kcc.banking.domain.business_day.dto.response.BusinessDay;

import com.kcc.banking.domain.business_day.mapper.BusinessDayMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class BusinessDayService {

    private final BusinessDayMapper businessDayMapper;

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
     * 1. DB 업데이트(현재 영업일 지정 상태를 변경 FALSE로 변경 + 다음 영업일을 오픈 및 현재 영업일로 지정)
     * 2. 변경사항 객체에 반영()
     */
    public void businessDayChange(BusinessDay currentBusinessDay, BusinessDay nextBusinessDay) {

        // 1
        String loginMemberId = String.valueOf(AuthenticationUtils.getLoginMemberId());

        int updateResult1 = finishCurrentBusinessDay(currentBusinessDay.getBusinessDate(), loginMemberId);
        int updateResult2 = openBusinessDay(nextBusinessDay.getBusinessDate(), loginMemberId);

        // 2
        if(updateResult1 == 1 && updateResult2 == 1) {
            reflectUpdateResult(currentBusinessDay, nextBusinessDay);
        }
    }

    /**
     * @Description
     * 영업일 변경 -> 현재 영업일 상태를 FALSE처리
     */
    public int finishCurrentBusinessDay(String targetDate, String modifierId) {
        return businessDayMapper.update(BusinessDayUpdate.builder()
                .targetDate(targetDate)
                .isCurrentBusinessDay("FALSE")
                .modifierId(modifierId)
                .build()
        );

    }

    /**
     * @Description
     * 현재 영업일을 영업 전으로 돌린다.
     */
    public int resetBusinessDay(String targetDate, String modifierId) {
        return businessDayMapper.update(BusinessDayUpdate.builder()
                .targetDate(targetDate)
                .status("SCHEDULED")
                .isCurrentBusinessDay("FALSE")
                .modifierId(modifierId)
                .build()
        );

    }
    /**
     * @Description
     * 영업일 변경 시 사용
     */
    public int openBusinessDay(String targetDate, String modifierId) {
        return businessDayMapper.update(BusinessDayUpdate.builder()
                .targetDate(targetDate)
                .status("OPEN")
                .isCurrentBusinessDay("TRUE")
                .modifierId(modifierId)
                .build()
        );

    }
    private void reflectUpdateResult(BusinessDay currentBusinessDay, BusinessDay nextBusinessDay) {
        currentBusinessDay.toClose();
        nextBusinessDay.toOpen();
    }

    public void businessDayStatusToClosed(String targetDate) {
        String loginMemberId = String.valueOf(AuthenticationUtils.getLoginMemberId());
        businessDayMapper.update(BusinessDayUpdate.builder()
                .targetDate(targetDate)
                .status("CLOSED")
                .modifierId(loginMemberId)
                .build()
        );
    }
}
