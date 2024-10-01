package com.kcc.banking.domain.interest.mapper;

import com.kcc.banking.domain.interest.dto.request.InterestCreate;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface InterestMapper {

    void createInterest(InterestCreate interestCreate);
}
