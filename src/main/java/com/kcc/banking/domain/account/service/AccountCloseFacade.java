package com.kcc.banking.domain.account.service;


import com.kcc.banking.common.exception.ErrorCode;
import com.kcc.banking.common.exception.custom_exception.BadRequestException;
import com.kcc.banking.domain.account.dto.request.AccountClose;
import com.kcc.banking.domain.account.dto.request.AccountUpdate;
import com.kcc.banking.domain.account.dto.request.FixedSavingsAccountClose;
import com.kcc.banking.domain.account.dto.request.FlexibleSavingsAccountClose;
import com.kcc.banking.domain.account.dto.response.AccountCloseResult;
import com.kcc.banking.domain.account.dto.response.CloseFixedAccountDetail;
import com.kcc.banking.domain.account.dto.response.CloseSavingsAccountTotal;
import com.kcc.banking.domain.account.dto.response.CloseSavingsFlexibleAccountTotal;
import com.kcc.banking.domain.account.mapper.AccountMapper;
import com.kcc.banking.domain.auto_transfer.service.AutoTransferService;
import com.kcc.banking.domain.business_day.dto.response.BusinessDay;
import com.kcc.banking.domain.business_day_close.service.BusinessDayCloseService;
import com.kcc.banking.domain.common.dto.request.CurrentData;
import com.kcc.banking.domain.common.service.CommonService;
import com.kcc.banking.domain.employee.dto.request.BusinessDateAndBranchId;
import com.kcc.banking.domain.interest.dto.response.InterestDetails;
import com.kcc.banking.domain.interest.service.InterestService;
import com.kcc.banking.domain.trade.dto.request.TradeCreate;
import com.kcc.banking.domain.trade.service.TradeService;
import com.kcc.banking.domain.reserve_transfer.service.ReserveTransferService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.List;

@RequiredArgsConstructor
@Component
public class AccountCloseFacade {

    private final AccountService accountService;
    private final CommonService commonService;
    private final InterestService interestService;
    private final TradeService tradeService;
    private final BusinessDayCloseService businessDayCloseService;

    private final AutoTransferService autoTransferService;
    private final AccountMapper accountMapper;
    private final ReserveTransferService reserveTransferService;


    /**
     * @param accountId
     * @return
     * @Discription 계좌번호로 기본 계좌 정보, 고객 정보, 상품의 기간과 이름 조회
     * 자유적금 해지를 위한 기본 정보 불러오기
     * 1. 개설일 + 기간 = 만기일
     * 1-1. 만기일 이전에 해지 할 시 이자내역 다시 계산
     * 1-2. 만기일 / 만기일 이후 해지 시 그대로 반환
     * 재계산된 이자 내역 추가
     * @param accountId
     * @return
     */
    public CloseSavingsFlexibleAccountTotal getFlexibleSavingsAccount(String accountId) {
        // 자유 적금 정보 불러오기
        CloseSavingsFlexibleAccountTotal closeSavingsFlexibleAccountTotal = accountService.getCloseSavingsFlexibleAccountById(accountId);

        // 해지 종류 판별
        String businessDateStr = commonService.getCurrentBusinessDay().getBusinessDate();
        String openDateStr = closeSavingsFlexibleAccountTotal.getOpenDate();
        String periodStr = closeSavingsFlexibleAccountTotal.getPeriod();

        // DateTimeFormatter 정의
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        // String을 LocalDate, LocalDateTime으로 변환
        LocalDate businessDate = LocalDateTime.parse(businessDateStr, dateTimeFormatter).toLocalDate();
        LocalDateTime openDate = LocalDateTime.parse(openDateStr, dateTimeFormatter);
        // period 값을 개월로 더하기
        int periodMonths = Integer.parseInt(periodStr);
        LocalDateTime maturityDate = openDate.plusMonths(periodMonths);  // 만기일 계산

        // 만기일 계산
        LocalDate maturityDateOnly = maturityDate.toLocalDate();  // 만기일을 LocalDate로 변환
        // 만기일 설정
        if (closeSavingsFlexibleAccountTotal.getExpectedExpireDate() == null) {
            closeSavingsFlexibleAccountTotal.setExpectedExpireDate(maturityDateOnly.toString());
        }

        // 경과 일수 및 계약 기간 계산
        long diffDays = ChronoUnit.DAYS.between(openDate.toLocalDate(), businessDate);
        long contractDays = ChronoUnit.DAYS.between(openDate.toLocalDate(), maturityDateOnly);

        // 최종 이율 계산
        BigDecimal finalInterestRate;
        boolean isEarlyTermination = false;
        boolean isMaturityTermination = false;

        // 기본 이율 (기본 금리 + 우대금리 포함)
        BigDecimal baseInterestRate = closeSavingsFlexibleAccountTotal.getInterestRate().add(closeSavingsFlexibleAccountTotal.getPreferentialInterestRate()).divide(BigDecimal.valueOf(100), 4, RoundingMode.DOWN);  // 소수로 변환

        // 조건 비교
        if (businessDate.isBefore(maturityDateOnly)) {
            System.out.println("중도 해지");
            isEarlyTermination = true;

            // 중도 해지에 따른 이율 계산
            if (diffDays < 30) {
                finalInterestRate = BigDecimal.valueOf(0.001);  // 연 0.1%
            } else if (diffDays < 90) {
                finalInterestRate = BigDecimal.valueOf(0.0015);  // 연 0.15%
            } else if (diffDays < 180) {
                finalInterestRate = BigDecimal.valueOf(0.002);  // 연 0.2%
            } else if (diffDays < 270) {
                BigDecimal calculatedRate = BigDecimal.valueOf(0.6)
                        .multiply(BigDecimal.valueOf(diffDays))
                        .divide(BigDecimal.valueOf(contractDays), 4, RoundingMode.DOWN)
                        .multiply(baseInterestRate);
                finalInterestRate = calculatedRate.max(BigDecimal.valueOf(0.002));
            } else if (diffDays < 330) {
                BigDecimal calculatedRate = BigDecimal.valueOf(0.7)
                        .multiply(BigDecimal.valueOf(diffDays))
                        .divide(BigDecimal.valueOf(contractDays), 4, RoundingMode.DOWN)
                        .multiply(baseInterestRate);
                finalInterestRate = calculatedRate.max(BigDecimal.valueOf(0.002));
            } else {
                BigDecimal calculatedRate = BigDecimal.valueOf(0.9)
                        .multiply(BigDecimal.valueOf(diffDays))
                        .divide(BigDecimal.valueOf(contractDays), 4, RoundingMode.DOWN)
                        .multiply(baseInterestRate);
                finalInterestRate = calculatedRate.max(BigDecimal.valueOf(0.002));
            }
        } else if (businessDate.isEqual(maturityDateOnly)) {
            System.out.println("만기 해지");
            finalInterestRate = baseInterestRate;
            isMaturityTermination = true;
        } else {
            System.out.println("만기 후 해지");
            finalInterestRate = baseInterestRate;
            // 만기 후 해지 이율 계산
            long diffDaysAfterMaturity = ChronoUnit.DAYS.between(maturityDateOnly, businessDate);

            if (diffDaysAfterMaturity <= 30) {
                //finalInterestRate = baseInterestRate.multiply(BigDecimal.valueOf(0.5)).setScale(4, RoundingMode.DOWN);  // 기본금리의 1/2
            } else {
                //finalInterestRate = baseInterestRate.multiply(BigDecimal.valueOf(0.25)).setScale(4, RoundingMode.DOWN);  // 기본금리의 1/4
            }
        }

        // 최종 이율 및 결과 출력
/*
        System.out.println("최종 이율: " + finalInterestRate.setScale(4, RoundingMode.DOWN));
        System.out.println("중도 해지 여부: " + isEarlyTermination);
        System.out.println("만기 해지 여부: " + isMaturityTermination);
        System.out.println("경과 일수: " + diffDays);
*/
        // 최종 적용 이율
        closeSavingsFlexibleAccountTotal.setFinalInterestRate(finalInterestRate);

        List<InterestDetails> interestDetailsList = interestService.getInterestDetailsByAccountId(accountId);
        // 중도 해지 시
        if (isEarlyTermination) {
            closeSavingsFlexibleAccountTotal.setCloseType(CloseSavingsFlexibleAccountTotal.CloseType.EARLY_TERMINATION);
            // 1개월 미만일 시
            if (interestDetailsList.isEmpty()) {
                // 연 이자율로 나눠서 계산
                BigDecimal intersetSum = closeSavingsFlexibleAccountTotal.getBalance().multiply(finalInterestRate.divide(BigDecimal.valueOf(12), 8, RoundingMode.DOWN));
                // 이자 합: 세전
                closeSavingsFlexibleAccountTotal.setTotalInterestSum(intersetSum);
                // 이자 합 : 세후
                closeSavingsFlexibleAccountTotal.setTotalInterestSumAfterTax(intersetSum
                        .multiply(closeSavingsFlexibleAccountTotal.getTaxRate())
                        .setScale(4, RoundingMode.DOWN));

                // 해지 시 최종 지급액
                // 최종 지급액 계산 : 10의 자리 버림
                BigDecimal totalAmount = closeSavingsFlexibleAccountTotal.getBalance()
                        .add(closeSavingsFlexibleAccountTotal.getTotalInterestSumAfterTax())
                        .divide(new BigDecimal("10"), 0, RoundingMode.DOWN)
                        .multiply(new BigDecimal("10"));
                closeSavingsFlexibleAccountTotal.setTotalAmount(totalAmount);

                // 이자 내역 : null
                closeSavingsFlexibleAccountTotal.setInterestDetailsList(null);
                return closeSavingsFlexibleAccountTotal;
            } else {
                // 이자 내역 재계산
                BigDecimal totalInterestSum = BigDecimal.ZERO;  // 총 이자 합계 변수 초기화

                for (int i = 0; i < interestDetailsList.size(); i++) {
                    InterestDetails interestDetails = interestDetailsList.get(i);
                    interestDetails.setInterestRate(finalInterestRate);
                    interestDetails.setPreferentialInterestRate(BigDecimal.ZERO);  // 중도 해지 시 우대금리 제거
                
                    // 단리로 계산하기에 잔액 기준으로 계산
                    BigDecimal subtractBalance = interestDetails.getBalance();
                    // 첫 번째 달은 이전 잔액이 없으므로 계산하지 않음
/*
                    if (i == 0) {
                        subtractBalance = interestDetails.getBalance();  // 첫 번째 달의 잔액을 그대로 사용
                    } else {
                        // 현재 잔액에서 이전 잔액을 뺀 값 (이번 달 새로 추가된 금액)
                        subtractBalance = interestDetails.getBalance().subtract(interestDetailsList.get(i - 1).getBalance());
                    }
*/

                    // 단리 이자 계산: 이번 달 새로 추가된 금액 * 월 이율(이율 / 12)
                    BigDecimal interestAmount = subtractBalance.multiply(finalInterestRate.divide(BigDecimal.valueOf(12), 4, RoundingMode.DOWN)).setScale(4, RoundingMode.DOWN);
                    interestDetails.setAmount(interestAmount);
                    // 이자 합계에 더하기
                    totalInterestSum = totalInterestSum.add(interestAmount);
                }
            }
        } else if (isMaturityTermination) {
            closeSavingsFlexibleAccountTotal.setCloseType(CloseSavingsFlexibleAccountTotal.CloseType.MATURITY_TERMINATION);
        } else {
            closeSavingsFlexibleAccountTotal.setCloseType(CloseSavingsFlexibleAccountTotal.CloseType.POST_MATURITY_TERMINATION);
        }
        return CloseSavingsFlexibleAccountTotal.of(closeSavingsFlexibleAccountTotal, interestDetailsList);
    }


    /**
     * @param accountClose
     * @return
     * @Discription - 자유적금 계좌 해지
     * 1. 계좌 해지 거래 내역 생성
     * 1-2. 행원 시재금 변경(출금)
     * 2. 계좌 잔액 및 상태 변경
     * 3. 이자 지급일 및 상태 변경
     */
    @Transactional(rollbackFor = Exception.class)
    public AccountCloseResult closeFlexibleSavingsAccount(FlexibleSavingsAccountClose accountClose) {
        CurrentData currentData = commonService.getCurrentData();
        BusinessDay currentBusinessDay = commonService.getCurrentBusinessDay();

        // OPEN 상태가 아니라면
        if (!currentBusinessDay.getStatus().equals("OPEN")) {
            throw new BadRequestException(ErrorCode.NOT_OPEN);
        }

        // 거래번호 조회 (trade_num_seq): return 거래번호 + 1
        Long tradeNumber = tradeService.getNextTradeNumberVal();

        // 재사용 위한 accountClose 설정
        AccountClose basicAccountClose = AccountClose.builder()
                .accId(accountClose.getAccId())
                .amount(accountClose.getAmount())
                .status(accountClose.getStatus())
                .tradeType(accountClose.getTradeType())
                .description(accountClose.getDescription())
                .build();

        // 1. 해지 거래 생성
        TradeCreate tradeResult = tradeService.createCloseTrade(basicAccountClose, currentData, tradeNumber);

        // 2. 계좌 잔액 0, 상태 CLS
        AccountUpdate accountUpdate = accountService.updateByCloseTrade(basicAccountClose, currentData);
        // 2-1. 행원 마감 출금액 변경
        businessDayCloseService.updateTradeAmount(accountClose.getAmount(), currentData, accountClose.getTradeType());

        // 3. 이자내역 지급 처리
        try {
            // 중도 해지라면 재계산된 이자 내역으로 업데이트
            if (accountClose.getCloseType().equals(CloseSavingsFlexibleAccountTotal.CloseType.EARLY_TERMINATION.getDescription())) {
                interestService.updateByCloseWithInterestList(accountClose, currentData);
            } else {
                // 기존 해지 재사용
                interestService.updateByClose(basicAccountClose, currentData);
            }
        } catch (Exception e) {
            throw new BadRequestException(ErrorCode.FAIL_TO_CLOSE);
        }

        // 해지 계좌 정보 조회
        CloseSavingsFlexibleAccountTotal closeSavingsFlexibleAccountById = accountService.getCloseSavingsFlexibleAccountById(accountClose.getAccId());

        return AccountCloseResult.builder()
                .accountId(accountClose.getAccId())
                .accountStatus(accountUpdate.getStatus())
                .openDate(Timestamp.valueOf(LocalDateTime.parse(closeSavingsFlexibleAccountById.getOpenDate(), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))))
                .accountPreInterRate(closeSavingsFlexibleAccountById.getPreferentialInterestRate())
                .accountBal(accountUpdate.getBalance())
                .customerName(closeSavingsFlexibleAccountById.getCustomerName())
                .customerId(closeSavingsFlexibleAccountById.getCustomerId())
                .productName(closeSavingsFlexibleAccountById.getProductName())
                .productInterRate(closeSavingsFlexibleAccountById.getInterestRate())
                .productTaxRate(closeSavingsFlexibleAccountById.getTaxRate())
                .amountSum(accountClose.getAmount())
                .build();
    }


    /**
     *  정기 적금 해지
     *  1. 해지 거래 생성
     *  2. 잔액 변경
     *  3. 행원 마감액 변경
     *  4. 자동이체 계좌 존재시 상태 변경 :  해야됨
     * @param accountClose
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public AccountCloseResult closeFixedSavingsAccount(FixedSavingsAccountClose accountClose) {
        CurrentData currentData = commonService.getCurrentData();
        BusinessDay currentBusinessDay = commonService.getCurrentBusinessDay();

        // OPEN 상태가 아니라면
        if (!currentBusinessDay.getStatus().equals("OPEN")) {
            throw new BadRequestException(ErrorCode.NOT_OPEN);
        }

        // 거래번호 조회 (trade_num_seq): return 거래번호 + 1
        Long tradeNumber = tradeService.getNextTradeNumberVal();

        // 재사용 위한 accountClose 설정
        AccountClose basicAccountClose = AccountClose.builder()
                .accId(accountClose.getAccId())
                .amount(accountClose.getAmount())
                .status(accountClose.getStatus())
                .tradeType(accountClose.getTradeType())
                .description(accountClose.getDescription())
                .build();

        // 1. 해지 거래 생성
        TradeCreate tradeResult = tradeService.createCloseTrade(basicAccountClose, currentData, tradeNumber);

        // 2. 계좌 잔액 0, 상태 CLS
        AccountUpdate accountUpdate = accountService.updateByCloseTrade(basicAccountClose, currentData);
        // 2-1. 행원 마감 출금액 변경
        businessDayCloseService.updateTradeAmount(accountClose.getAmount(), currentData, accountClose.getTradeType());

        // 자동이체 계좌 존재 시 active -> stop 상태 변경


        // 해지 계좌 정보 조회
        CloseSavingsAccountTotal closeSavingsAccountById = accountService.findCloseSavingsAccountDetail(accountClose.getAccId());

        return AccountCloseResult.builder()
                .accountId(accountClose.getAccId())
                .accountStatus(accountUpdate.getStatus())
                .openDate(Timestamp.valueOf(LocalDateTime.parse(closeSavingsAccountById.getOpenDate(), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))))
                .accountPreInterRate(closeSavingsAccountById.getProductInterestRate())
                .accountBal(accountUpdate.getBalance())
                .customerName(closeSavingsAccountById.getCustomerName())
                .customerId(closeSavingsAccountById.getCustomerId())
                .productName(closeSavingsAccountById.getProductName())
                .productInterRate(closeSavingsAccountById.getAccountInterestRate())
                .productTaxRate(closeSavingsAccountById.getProductTaxRate())
                .amountSum(accountClose.getAmount())
                .build();
    }


    /**
     *   중도 해지
     *   만기 해지
     *   만기 후 해지 : 만기까지 약정이율의 이자 + 만기 이후 새로운 이율의 이자
     *   총 이율 구하는 함수
     * 
     * @param accountId
     * @return
     */
    public CloseSavingsAccountTotal CalculateInsertOtCloseFixedSavingsAccount(String accountId){

        CloseSavingsAccountTotal closeSavingsAccountTotal = accountMapper.findCloseSavingsAccountDetail(accountId);

        // 해지 종류 판별
        String businessDateStr = commonService.getCurrentBusinessDay().getBusinessDate();
        String openDateStr = closeSavingsAccountTotal.getOpenDate();
        String periodStr = closeSavingsAccountTotal.getProductPeriod();

        // DateTimeFormatter 정의
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        // String을 LocalDate, LocalDateTime으로 변환
        LocalDate businessDate = LocalDateTime.parse(businessDateStr, dateTimeFormatter).toLocalDate();
        LocalDateTime openDate = LocalDateTime.parse(openDateStr, dateTimeFormatter);
        // period 값을 개월로 더하기
        int periodMonths = Integer.parseInt(periodStr);
        LocalDateTime maturityDate = openDate.plusMonths(periodMonths);  // 만기일 계산

        // 만기일 계산
        LocalDate maturityDateOnly = maturityDate.toLocalDate();  // 만기일을 LocalDate로 변환
        // 만기일 설정
        if(closeSavingsAccountTotal.getExpectedExpireDate() == null){
            closeSavingsAccountTotal.setExpectedExpireDate(maturityDateOnly.toString());
        }

        // 경과 일수 및 계약 기간 계산
        long diffDays = ChronoUnit.DAYS.between(openDate.toLocalDate(), businessDate);
        long contractDays = ChronoUnit.DAYS.between(openDate.toLocalDate(), maturityDateOnly);

        // 최종 이율 계산
        BigDecimal finalInterestRate;
        // 만기후 이율 계산
        BigDecimal afterFinalInterestRate;
        
        boolean isEarlyTermination = false;
        boolean isMaturityTermination = false;

        // 기본 이율(상품이율) + 우대이율(계좌이율) [ 0.04% : (X) ] -> 0.0400
        BigDecimal baseInterestRate = closeSavingsAccountTotal.getProductInterestRate().add(closeSavingsAccountTotal.getAccountInterestRate()).divide(BigDecimal.valueOf(100), 4, RoundingMode.DOWN);  // 소수로 변환

        // 조건 비교
        if (businessDate.isBefore(maturityDateOnly)) {
            System.out.println("중도 해지");
            isEarlyTermination = true;

            // 중도 해지에 따른 이율 계산
            if (diffDays < 30) {
                finalInterestRate = BigDecimal.valueOf(0.001);  // 연 0.1%
            } else if (diffDays < 90) {
                finalInterestRate = BigDecimal.valueOf(0.0015);  // 연 0.15%
            } else if (diffDays < 180) {
                finalInterestRate = BigDecimal.valueOf(0.002);  // 연 0.2%
            } else if (diffDays < 270) {
                BigDecimal calculatedRate = BigDecimal.valueOf(0.6)
                        .multiply(BigDecimal.valueOf(diffDays))
                        .divide(BigDecimal.valueOf(contractDays), 4, RoundingMode.DOWN)
                        .multiply(baseInterestRate);
                finalInterestRate = calculatedRate.max(BigDecimal.valueOf(0.002));
            } else if (diffDays < 330) {
                BigDecimal calculatedRate = BigDecimal.valueOf(0.7)
                        .multiply(BigDecimal.valueOf(diffDays))
                        .divide(BigDecimal.valueOf(contractDays), 4, RoundingMode.DOWN)
                        .multiply(baseInterestRate);
                finalInterestRate = calculatedRate.max(BigDecimal.valueOf(0.002));
            } else {
                BigDecimal calculatedRate = BigDecimal.valueOf(0.9)
                        .multiply(BigDecimal.valueOf(diffDays))
                        .divide(BigDecimal.valueOf(contractDays), 4, RoundingMode.DOWN)
                        .multiply(baseInterestRate);
                finalInterestRate = calculatedRate.max(BigDecimal.valueOf(0.002));
            }
        } else if (businessDate.isEqual(maturityDateOnly)) {
            System.out.println("만기 해지");
            finalInterestRate = baseInterestRate;
            isMaturityTermination = true;
        } else {
            System.out.println("만기 후 해지");

            finalInterestRate = baseInterestRate;
            // 만기 후 해지 이율 계산
            long diffDaysAfterMaturity = ChronoUnit.DAYS.between(maturityDateOnly, businessDate);

            if (diffDaysAfterMaturity <= 30) {
                afterFinalInterestRate = baseInterestRate.multiply(BigDecimal.valueOf(0.5)).setScale(4, RoundingMode.DOWN);  // 기본금리의 1/2
            } else {
                afterFinalInterestRate = baseInterestRate.multiply(BigDecimal.valueOf(0.25)).setScale(4, RoundingMode.DOWN);  // 기본금리의 1/4
            }
        }

        // 최종 적용 이율
        closeSavingsAccountTotal.setFinalInterestRate(finalInterestRate);

        System.out.println("최종 적용 이율 finalInterestRate >>>>>>>>>>>"+finalInterestRate);

        System.out.println("최종 이율: " + finalInterestRate.setScale(4, RoundingMode.DOWN));
        System.out.println("중도 해지 여부: " + isEarlyTermination);
        System.out.println("만기 해지 여부: " + isMaturityTermination);
        System.out.println("경과 일수: " + diffDays);

        // 정기 입금 금액 및 정기 입금 총 횟수
        // 해지 계좌번호
        String accId = closeSavingsAccountTotal.getAccountId();
        CloseFixedAccountDetail closeFixedAccountDetail= accountService.findCloseFixedAccountDetail(accId);

        // 정기 입금 금액
        BigDecimal amount = closeFixedAccountDetail.getAmount();
        // 정기 입금 총 횟수
        int totalDepositCount = closeFixedAccountDetail.getTotalTransferCount();

        BigDecimal totalInterest;

        if(isEarlyTermination){
            System.out.println("중도 해지 일때 >>>>");
            // 중도 해지 일때
            closeSavingsAccountTotal.setCloseType(CloseSavingsFlexibleAccountTotal.CloseType.EARLY_TERMINATION);
            // 원금 * 연이자율 * 기간(개월 수)
            totalInterest = calculateFixedInterest(amount,finalInterestRate,totalDepositCount);
            System.out.println("중도 해지  >>>>> "+closeSavingsAccountTotal.getCloseType());
            System.out.println(totalInterest);
            closeSavingsAccountTotal.setInterestCashSum(totalInterest);

        }else if(isMaturityTermination){
            System.out.println("만기 일때 >>>>");
            // 만기 일때
            closeSavingsAccountTotal.setCloseType(CloseSavingsFlexibleAccountTotal.CloseType.MATURITY_TERMINATION);
            // 원금 * 연이자율 * 기간(개월 수)
            totalInterest = calculateFixedInterest(amount,finalInterestRate,totalDepositCount);
            System.out.println("만기 해지  >>>>> "+closeSavingsAccountTotal.getCloseType());
            System.out.println(totalInterest);

            closeSavingsAccountTotal.setInterestCashSum(totalInterest);


        }else{

            System.out.println("만기 후해지 일때 >>>>");
            // 만기 후 해지 일때
            closeSavingsAccountTotal.setCloseType(CloseSavingsFlexibleAccountTotal.CloseType.POST_MATURITY_TERMINATION);
            // 만기까지 이자 + 만기 이후 이자

            // 만기까지 이자: 원금 * 연이자율 * 기간(개월 수)
            totalInterest = calculateFixedInterest(amount,finalInterestRate,parseIntWithoutZero(closeSavingsAccountTotal.getProductPeriod()));


            afterFinalInterestRate = baseInterestRate;
            // 만기 이후 입금 개월 수 : totalDepositCount - productPeriod
            // 만기 이후 이자:BigDecimal principal, BigDecimal annualInterestRate, int months
            int afterMonth = (totalDepositCount-parseIntWithoutZero(closeSavingsAccountTotal.getProductPeriod()));
            BigDecimal afterTotalInterest = calculateMonthlyInterest(amount,afterFinalInterestRate,afterMonth);

            System.out.println("만기 후 이율 계산  >>>>> "+closeSavingsAccountTotal.getCloseType());
            System.out.println("totalInterest >>>> "+totalInterest);
            System.out.println("afterTotalInterest ,afterMonth >>>> "+afterTotalInterest+","+afterMonth);
            System.out.println("afterTotalInterest+totalInterest >>>> "+ afterTotalInterest.add(totalInterest));


            closeSavingsAccountTotal.setInterestCashSum(afterTotalInterest.add(totalInterest));

        }


        return CloseSavingsAccountTotal.of(closeSavingsAccountTotal);
    }

    public int parseIntWithoutZero(String input) {
        if (input != null && !input.isEmpty()) {
            // parseInt로 변환하면 자동으로 앞의 '0'이 제거됩니다.
            return Integer.parseInt(input);
        } else {
            throw new IllegalArgumentException("입력 값이 null이거나 빈 문자열입니다.");
        }
    }

    /**
     * 정기적금 이자 계산 (단리 방식)
     * @param principal 원금 (정기적금의 초기 금액)
     * @param finalInterestRate 최종 이자율 (연 이자율) : 단위 : 실제 이율 [ 4% (x)) / 0.04 (0) ]
     * @param months 정기적금 기간 (개월)
     * @return 이자 금액
     *
     *
     */
    public BigDecimal calculateFixedInterest(BigDecimal principal, BigDecimal finalInterestRate, int months) {
        BigDecimal totalInterest = BigDecimal.ZERO;
        BigDecimal annualInterestRate = finalInterestRate;

        // 첫 번째 달: 연이율 * 12/12
        BigDecimal firstMonthInterestRate = annualInterestRate.multiply(BigDecimal.valueOf(12)).divide(BigDecimal.valueOf(12), 4, RoundingMode.DOWN);
        BigDecimal firstMonthInterest = principal.multiply(firstMonthInterestRate).divide(BigDecimal.valueOf(100), 4, RoundingMode.DOWN);
        totalInterest = totalInterest.add(firstMonthInterest);

        // 두 번째 달부터는 연이율 * 남은개월/12
        for (int i = 2; i <= months; i++) {
            BigDecimal remainingMonths = BigDecimal.valueOf(months - i + 1); // 남은 개월 수 (12 -> 11 -> 10 ...)
            BigDecimal monthlyInterestRate = annualInterestRate.multiply(remainingMonths).divide(BigDecimal.valueOf(12), 4, RoundingMode.DOWN);

            // 해당 달 이자 계산
            BigDecimal interestForMonth = principal.multiply(monthlyInterestRate).divide(BigDecimal.valueOf(100), 4, RoundingMode.DOWN);
            totalInterest = totalInterest.add(interestForMonth);
        }

        return totalInterest;
    }

    // 예금 이자 단리계산 방식
    public static BigDecimal calculateMonthlyInterest(BigDecimal principal, BigDecimal afterFinalInterestRate, int months) {
        BigDecimal monthlyInterestRate = afterFinalInterestRate.divide(BigDecimal.valueOf(12), 6, RoundingMode.DOWN); // 월 이자율 계산
        BigDecimal afterTotalInterest = BigDecimal.ZERO; // 총 이자
        BigDecimal oneHundred = BigDecimal.valueOf(100); // 백분율 계산을 위한 상수

        System.out.println("| 월  | 이자율 (%)  | 발생 이자 (원)  | 누적 이자 (원)  |");
        System.out.println("|-----|--------------|-----------------|-----------------|");

        // 매달 이자 계산
        for (int i = 0; i < months; i++) {
            // 월 이자율을 적용한 이자 계산
            BigDecimal interestForMonth = principal.multiply(monthlyInterestRate).divide(oneHundred, 6, RoundingMode.DOWN);
            afterTotalInterest = afterTotalInterest.add(interestForMonth); // 누적 이자

            // 매달 발생한 이자와 누적 이자 출력
            System.out.printf("| %2d  | %6.2f%%  | %,15.2f  | %,15.2f |\n", i + 1, monthlyInterestRate.multiply(BigDecimal.valueOf(100)), interestForMonth, afterTotalInterest);
        }
        return afterTotalInterest;
    }

    /**
     * @Description
     * 정기적금 계좌 해지 정보/ (예금 제외)
     *
     *
     */

    public CloseSavingsAccountTotal getCloseSavingsAccount(String accountId){

        // 1. 정기적금 해지 정보 총 출력
        /*
          1. 계좌정보
          2. 자동이체 정보
          3. 적금 상품 정보
          4. 이율 계산 정보 - 함수사용 추가예정 CalculateInsertOtCloseFixedSavingsAccount()
        */

        //1. 계좌정보 + 2. 자동이체 정보 + 3. 적금 상품 정보  호출
        CloseSavingsAccountTotal csat =  accountMapper.findCloseSavingsAccountDetail(accountId);
        System.out.println("계좌정보 + 2. 자동이체 정보 + 3. 적금 상품 정보  호출 >>>>"+csat.toString());

        // csat가 null인지 확인
        if (csat == null) {
            // 적절한 예외 처리 또는 기본값 설정
            throw new IllegalArgumentException("계좌 ID에 대한 정보를 찾을 수 없습니다: " + accountId);
        }
        String autoTransferId = csat.getAutoTransferId();

        //이체 횟수조회(거래내역조회)
        csat.setAutoTransferCount(reserveTransferService.countAutoReserveSuccess(autoTransferId));

        // 이자 계산
        csat = CalculateInsertOtCloseFixedSavingsAccount(accountId);

        System.out.println("결과 확인>>>>>>>>>>"+csat.toString());


        // 정보 담김.
        return csat;

    }



}
