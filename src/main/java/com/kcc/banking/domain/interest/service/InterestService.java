package com.kcc.banking.domain.interest.service;

import com.kcc.banking.domain.account.dto.request.AccountClose;
import com.kcc.banking.domain.account.dto.request.FlexibleSavingsAccountClose;
import com.kcc.banking.domain.account.dto.response.AccountDetailForInterest;
import com.kcc.banking.domain.account.service.AccountService;
import com.kcc.banking.domain.common.dto.request.CurrentData;
import com.kcc.banking.domain.common.service.CommonService;
import com.kcc.banking.domain.interest.dto.request.*;
import com.kcc.banking.domain.interest.dto.response.InterestDetails;
import com.kcc.banking.domain.interest.dto.response.InterestSum;
import com.kcc.banking.domain.employee.dto.request.BusinessDateAndBranchId;
import com.kcc.banking.domain.interest.mapper.InterestMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class InterestService {

    private final InterestMapper interestMapper;
    private final AccountService accountService;
    private final CommonService commonService;

    /**
     * @param tradeNumber
     * @param businessDateAndBranchId
     * @Discription 1. 지점 마감 tradeNumber와 동일한 이자 내역 생성
     * 2. 보통예금 / 자율적금 분기처리
     * 2-1. 보통에금 - 매일 일자별 이자 생성
     * 3. 매월 1일인지 영업일 확인
     * 3-1. 매월 1일이라면 복리를 위한 계좌별 이자내역 합산 가져오기
     * 4. 자율적금 단리 / 복리 분기
     * 4-1. 자율적금 단리 계산
     * 4-2. 자율적금 복리 계산
     */
    public void createInterest(String tradeNumber, BusinessDateAndBranchId businessDateAndBranchId) {
        CurrentData currentData = commonService.getCurrentData();
        // 지점 번호로 등록된 계좌 목록 불러오기
        List<AccountDetailForInterest> accountList = accountService.getAccountListByBranchId(currentData.getBranchId());
        // 새로운 이자 내역 생성
        // 2-1. 보통예금의 경우 ( 단리 )
        // period로 구분
        List<InterestCreate> interestCreateList = accountList.stream().filter(account -> account.getPeriod().equals("00")).map(account -> InterestCreate.of(account, currentData.getEmployeeId(), businessDateAndBranchId, tradeNumber)).toList();
        // 이자 생성
        interestCreateList.forEach(interestMapper::createInterest);
        // 4. 매월 1일인지 영업일 확인
        LocalDateTime date = LocalDateTime.parse(businessDateAndBranchId.getBusinessDate(), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

        // 매월 1일이라면
        if (date.getDayOfMonth() == 1) {
        // 복리 계산을 위한 이자내역 합산 가져오기
        // 복리 계좌의 이자만 합산

        // interestSavingSumList가 null일 경우 빈 리스트로 초기화하고 null 항목 필터링
        List<InterestSum> interestSavingSumList = Optional.ofNullable(
                accountList.stream()
                        .filter(account -> "COMPOUND".equals(account.getInterestCalculationMethod()))
                        .map(account -> interestMapper.findInterestSum(account.getAccId()))
                        .filter(Objects::nonNull) // null 항목 제거
                        .toList()
        ).orElse(List.of());

        // 이자 내역 합산을 param으로 넘겨 새로운 이자 내역 생성
        List<SavingInterestCreate> interestSavingCreateList = accountList.stream()
                .filter(account -> "FLEXIBLE".equals(account.getProductType()))
                .map(account -> {
                    InterestSum matchingInterestSum = interestSavingSumList.stream()
                            .filter(interestSum -> interestSum.getAccountId().equals(account.getAccId()))
                            .findFirst()
                            .orElse(null); // 일치하는 InterestSum이 없을 경우 null 반환

                    // currentBusinessDate 만기일을 넘겼을 경우
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

                    LocalDate openDateParsed = LocalDate.parse(account.getOpenDate(), formatter);
                    LocalDate maturityDate = openDateParsed.plusMonths(Integer.parseInt(account.getPeriod()));
                    LocalDate currentDate = LocalDate.parse(currentData.getCurrentBusinessDate(), formatter);

                    // 만기일의 다음 달 1일을 기준으로 시작
                    LocalDate oneMonthAfterMaturityFirstDay = maturityDate.plusMonths(1).withDayOfMonth(1);
                    LocalDate twoMonthsAfterMaturityFirstDay = maturityDate.plusMonths(2).withDayOfMonth(1);

                    // 기본을 만기 전으로 설정
                    SavingInterestCreate.MaturityStatus afterType = SavingInterestCreate.MaturityStatus.BEFORE_MATURITY;
                    if (currentDate.isBefore(maturityDate)) {
                        afterType = SavingInterestCreate.MaturityStatus.BEFORE_MATURITY;
                    } else if (!currentDate.isBefore(oneMonthAfterMaturityFirstDay) && currentDate.isBefore(twoMonthsAfterMaturityFirstDay)) {
                        afterType = SavingInterestCreate.MaturityStatus.AFTER_MATURITY_WITHIN_ONE_MONTH;
                    } else if (!currentDate.isBefore(twoMonthsAfterMaturityFirstDay)) {
                        afterType = SavingInterestCreate.MaturityStatus.AFTER_MATURITY_BEYOND_ONE_MONTH;
                    }


                    return SavingInterestCreate.of(account, currentData.getEmployeeId(), businessDateAndBranchId, tradeNumber, matchingInterestSum, afterType);
                })
                .toList();


        // 생성한 자유적금 월별 이자 내역 INSERT
        interestSavingCreateList.forEach(interestMapper::createInterestSaving);
        }
    }


    /**
     * @Description 세전 이자액 합계(해지 시 사용)
     */
    public InterestSum getInterestSum(String accountId) {
        Optional<InterestSum> optInterestSum = Optional.ofNullable(interestMapper.findInterestSum(accountId));

        return optInterestSum.orElse(InterestSum.builder()
                .accountId(accountId)
                .amountSum(new BigDecimal("0")).build());
    }

    /**
     * @Description 세전 이자액 합계(해지 취소 시 사용)
     */
    public InterestSum getPreTaxInterestSum(AccountIdWithExpireDate awe) {
        return interestMapper.findPreTaxInterestSum(awe);
    }

    /**
     * @Discription 보통예금 해지 시 이자내역 지급변경
     * @param accountClose
     * @param currentData
     * @return
     */
    public int updateByClose(AccountClose accountClose, CurrentData currentData) {
        PaymentStatusUpdate paymentStatusUpdate = PaymentStatusUpdate.builder()
                .branchId(currentData.getBranchId())
                .payDate(currentData.getCurrentBusinessDate())
                .modifierId(currentData.getEmployeeId())
                .accId(accountClose.getAccId())
                .build();

        return interestMapper.updateByClose(paymentStatusUpdate);
    }

    /**
     * @Discription 보통예금 해지 취소에 따른 이자내역 변경
     * @param accId
     * @param currentData
     * @param expireDate
     * @return
     */
    public int updateByCloseCancel(String accId, CurrentData currentData, String expireDate) {

        PaymentStatusRollback paymentStatusRollback = PaymentStatusRollback.builder()
                .branchId(currentData.getBranchId())
                .modifierId(currentData.getEmployeeId())
                .accId(accId)
                .expireDate(expireDate).build();

        return interestMapper.updateByCloseCancel(paymentStatusRollback);
    }

    public void deleteInterest(CurrentData currentData, String prevBusinessDate) {
        BusinessDateAndBranchId businessDateAndBranchId = BusinessDateAndBranchId.builder()
                .branchId(String.valueOf(currentData.getBranchId()))
                .businessDate(prevBusinessDate).build();

        interestMapper.deleteInterest(businessDateAndBranchId);
    }

    public List<InterestDetails> getInterestDetailsByAccountId(String accountId) {
        return interestMapper.findInterestDetails(accountId);
    }

    /**
     * @Discription 자유적금 해지 - 중도해지의 경우 적용된 이율 및 단리 계산으로 인한 이자내역 변경
     * @param accountClose
     * @param currentData
     * @return
     */
    public void updateByCloseWithInterestList(FlexibleSavingsAccountClose accountClose, CurrentData currentData) {
        // InterestDetails 리스트를 PaymentUpdate 리스트로 변환
        List<PaymentUpdate> paymentUpdates = accountClose.getInterestDetailsList().stream()
                .map(detail -> PaymentUpdate.builder()
                        .accId(accountClose.getAccId())
                        .branchId(currentData.getBranchId())
                        .modifierId(currentData.getEmployeeId())
                        .payDate(currentData.getCurrentBusinessDate())
                        .creationDate(detail.getCreationDate())
                        .balance(detail.getBalance())
                        .interestRate(detail.getInterestRate())
                        .preferentialInterestRate(detail.getPreferentialInterestRate())
                        .amount(detail.getAmount())
                        .build())
                .toList();

        paymentUpdates.forEach(interestMapper::updateByCloseWithInterestList);
    }
}
