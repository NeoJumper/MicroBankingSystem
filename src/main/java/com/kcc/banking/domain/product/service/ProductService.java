package com.kcc.banking.domain.product.service;

import com.kcc.banking.domain.common.service.CommonService;
import com.kcc.banking.domain.product.dto.request.ProductSearch;
import com.kcc.banking.domain.product.dto.response.ProductDetail;
import com.kcc.banking.domain.product.mapper.ProductMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductMapper productMapper;
    private final CommonService commonService;

    public List<ProductDetail> getDepositProductList(ProductSearch productSearch) {
        String branchId = commonService.getCurrentBusinessDateAndBranchId().getBranchId();
        productSearch.setBranchId(Long.parseLong(branchId));
        return productMapper.findBySearchOption(productSearch);
    }
}
