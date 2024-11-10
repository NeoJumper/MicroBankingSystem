package com.kcc.banking.domain.auto_transfer.service;

import com.kcc.banking.common.exception.ErrorCode;
import com.kcc.banking.common.exception.custom_exception.BadRequestException;
import com.kcc.banking.domain.account.dto.request.*;
import com.kcc.banking.domain.account.dto.response.*;
import com.kcc.banking.domain.account.mapper.AccountMapper;
import com.kcc.banking.domain.auto_transfer.dto.request.AutoTransferCreate;
import com.kcc.banking.domain.auto_transfer.dto.response.AutoTransferList;
import com.kcc.banking.domain.auto_transfer.mapper.AutoTransferMapper;
import com.kcc.banking.domain.common.dto.request.CurrentData;
import com.kcc.banking.domain.common.service.CommonService;
import com.kcc.banking.domain.reserve_transfer.dto.request.ReserveTransferCreate;
import com.kcc.banking.domain.trade.dto.request.CloseAccount;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class AutoTransferService {
    private final AutoTransferMapper autoTransferMapper;

    public int createAutoTransferInfo(AutoTransferCreate autoTransferCreate) {
        return autoTransferMapper.insertAutoTransfer(autoTransferCreate);
    }

    public List<AutoTransferList> findScheduledAutoTransferList(){
        return autoTransferMapper.findScheduledAutoTransferList();
    }



}
