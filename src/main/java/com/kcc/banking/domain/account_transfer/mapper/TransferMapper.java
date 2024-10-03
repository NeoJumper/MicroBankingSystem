package com.kcc.banking.domain.account_transfer.mapper;


import com.kcc.banking.domain.account_transfer.dto.request.TransferCreate;
import com.kcc.banking.domain.account_transfer.dto.response.TransferDetail;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TransferMapper {
    // 거래 내역 추가
    int insertTransfer(TransferDetail transferDetail);
    // 계좌 잔액 업데이트
    int updateAccountBalance(TransferDetail transferDetail);
    // 거래 번호 추가
    long getNextTradeNumberVal();

    // 거래 번호로 내역 조회
    List<TransferDetail> getTradeDetailsByTradeNumber(Long tradeNumber);
}
