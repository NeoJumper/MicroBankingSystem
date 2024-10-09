package com.kcc.banking.domain.business_day.service;

import com.kcc.banking.common.exception.ErrorCode;
import com.kcc.banking.common.exception.custom_exception.BadRequestException;
import com.kcc.banking.domain.business_day.dto.request.BusinessDayChange;
import com.kcc.banking.domain.business_day.dto.response.BusinessDay;
import com.kcc.banking.domain.business_day_close.dto.request.BusinessDateAndEmployeeId;
import com.kcc.banking.domain.business_day_close.dto.response.ClosingData;
import com.kcc.banking.domain.business_day_close.dto.response.EmployeeClosingData;
import com.kcc.banking.domain.business_day_close.dto.response.ManagerClosingData;
import com.kcc.banking.domain.business_day_close.service.BusinessDayCloseService;
import com.kcc.banking.domain.common.service.CommonService;
import com.kcc.banking.domain.employee.dto.request.BusinessDateAndBranchId;
import com.kcc.banking.domain.interest.service.InterestService;
import com.kcc.banking.domain.trade.dto.response.TradeByCash;
import com.kcc.banking.domain.trade.service.TradeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@RequiredArgsConstructor
public class BusinessDayManagementFacade {

    private final BusinessDayService businessDayService;
    private final BusinessDayCloseService businessDayCloseService;
    private final CommonService commonService;
    private final TradeService tradeService;
    private final InterestService interestService;


    /**
     * @Description
     * 1. 영업일 변경(현재 영업일을 지정 상태를 FALSE 로 변경 + 다음 영업일을 오픈 및 현재 영업일로 지정)
     * 2. 업데이트 내용을 객체에 반영
     * 3. 예외 처리
     *      - 영업일 변경을 눌렀는데 현재 영업일이 영업중일 때 -> REQUIRED_BRANCH_CLOSING
     *      - 요청한 다음 영업일이 서버상의 현재 영업일일 때 -> THROW ALREADY_CHANGED_BUSINESS_DAY
     * 4. 마감 데이터(행원, 매니저) 생성
     */
    @Transactional(rollbackFor = Exception.class)
    public void changeBusinessDay(BusinessDayChange businessDayChange) {

        // 1
        BusinessDateAndBranchId businessDateAndBranchId = commonService.getCurrentBusinessDateAndBranchId();

        BusinessDay currentBusinessDay = businessDayService.getCurrentBusinessDay();
        BusinessDay nextBusinessDay = businessDayService.getNextBusinessDay();


        // 2
        businessDayService.businessDayChange(currentBusinessDay, nextBusinessDay);

        // 3
        String branchClosingStatus = businessDayCloseService.getBranchClosingStatusByDateAndId(businessDateAndBranchId);

        if(branchClosingStatus.equals("OPEN"))
            throw new BadRequestException(ErrorCode.REQUIRED_BRANCH_CLOSING);
        if(businessDayChange.getBusinessDateToChange().equals(currentBusinessDay.getBusinessDate().split(" ")[0]))
            throw new BadRequestException(ErrorCode.ALREADY_CHANGED_BUSINESS_DAY);


        // 4
        businessDayCloseService.createClosingData(businessDayChange);

    }

    /**
     * @Description
     * 1. 개인 마감 데이터 조회(거래내역과 마감 데이터)
     *      - 마감 데이터(현금 입출금액, 전일자 현금 잔액이며 금일 마감 금액은 마감시 변경)
     *      - 거래 내역 데이터(내역 상세 보기 시 조회 가능)
     *      - 거래 내역의 입출금 총액( 거래내역 합산)
     */
    public EmployeeClosingData getEmployeeClosingData() {

        BusinessDateAndEmployeeId currentBusinessDateAndEmployeeId = commonService.getCurrentBusinessDateAndEmployeeId();


        ClosingData closingData = businessDayCloseService.getClosingData(currentBusinessDateAndEmployeeId);
        List<TradeByCash> tradeByCashList = tradeService.findTradeByCash(currentBusinessDateAndEmployeeId);


        return EmployeeClosingData.of(closingData, tradeByCashList);
    }

    /**
     * @Description
     * 1. 개인 마감 데이터 조회(거래내역과 마감 데이터)
     *      - 사원들의 마감 내역(전일자 현금 잔액, 입출금액, 금일 마감 금액 등)
     *      - 지점의 전일자 현금 잔액
     *      - 지점의 현금 입출금액, 금일 마감 금액(사원들의 )
     */
    public ManagerClosingData getManagerClosingData() {

        BusinessDateAndBranchId currentBusinessDateAndBranchId = commonService.getCurrentBusinessDateAndBranchId();
        List<ClosingData> closingDataList = businessDayCloseService.getClosingDataList(currentBusinessDateAndBranchId);
        return ManagerClosingData.of(closingDataList);

    }
    /**
     * @Description
     * 1. 마감 데이터를 불러와 마감 상태 확인 -> CLOSED 라면 ALREADY_CLOSED_BUSINESS_DAY
     * 2. 예외 처리
     * 3. 사원 개인 마감
     */
    public void closeByEmployee() {
        // 1
        BusinessDateAndEmployeeId currentBusinessDateAndEmployeeId = commonService.getCurrentBusinessDateAndEmployeeId();

        ClosingData closingData = businessDayCloseService.getClosingData(currentBusinessDateAndEmployeeId);

        // 2
        if(closingData.getStatus().equals("CLOSED"))
            throw new BadRequestException(ErrorCode.ALREADY_CLOSED_BUSINESS_DAY);

        // 3
        businessDayCloseService.closeEmployeeBusinessDay(currentBusinessDateAndEmployeeId);

    }

    /**
     * @Description
     * 1. 마감 데이터 조회
     * 2. 예외 처리
     *      - 한명이라도 개인 마감 상태가 OPEN이라면 REQUIRED_EMPLOYEE_CLOSING
     *      - 마감하려고 하는 지점의 마감상태가 CLOSED라면 ALREADY_CLOSED_BUSINESS_DAY
     * 3. 지점 마감 상태를 CLOSED로 변경
     * 4. 영업일의 마감 상태도 CLOSED로 변경
     * 5. 지점마감의 거래번호를 가져와 이자 생성
     */
    public void closeByManager() {

        // 1
        BusinessDateAndBranchId businessDateAndBranchId = commonService.getCurrentBusinessDateAndBranchId();
        String currentBusinessDate = businessDateAndBranchId.getBusinessDate();

        List<ClosingData> closingDataList = businessDayCloseService.getClosingDataList(businessDateAndBranchId);

        ManagerClosingData managerClosingData =  ManagerClosingData.of(closingDataList);


        // 2

        if (managerClosingData.getClosingDataList().stream().map(ClosingData::getStatus).anyMatch("OPEN"::equals))
            throw new BadRequestException(ErrorCode.REQUIRED_EMPLOYEE_CLOSING);
        if(businessDayCloseService.getBranchClosingStatusByDateAndId(businessDateAndBranchId).equals("CLOSED"))
            throw new BadRequestException(ErrorCode.ALREADY_CLOSED_BUSINESS_DAY);

        // 3

        businessDayCloseService.closeBranchBusinessDay(businessDateAndBranchId);

        // 4

        businessDayService.businessDayStatusToClosed(currentBusinessDate);

        // 5
        String tradeNumber = businessDayCloseService.getClosingTradeNumber(businessDateAndBranchId);
        interestService.createInterest(tradeNumber, businessDateAndBranchId);
    }

}