package com.kcc.banking.domain.common.mapper;

import com.kcc.banking.domain.employee.dto.request.BusinessDateAndBranchId;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CommonMapper {

    String findClosingStatus(BusinessDateAndBranchId businessDateAndBranchId);
}

