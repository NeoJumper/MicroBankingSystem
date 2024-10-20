package com.kcc.banking.domain.product.mapper;

import com.kcc.banking.domain.product.dto.request.ProductSearch;
import com.kcc.banking.domain.product.dto.response.ProductDetail;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ProductMapper {

    List<ProductDetail> findBySearchOption(ProductSearch productSearch);
}
