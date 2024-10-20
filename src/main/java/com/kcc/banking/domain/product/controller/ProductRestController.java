package com.kcc.banking.domain.product.controller;

import com.kcc.banking.domain.product.dto.request.ProductSearch;
import com.kcc.banking.domain.product.dto.response.ProductDetail;
import com.kcc.banking.domain.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ProductRestController {

    private final ProductService productService;

    @GetMapping("/api/employee/deposit-products")
    public ResponseEntity<List<ProductDetail>> getProductsBySearchOption(@ModelAttribute ProductSearch productSearch){
        return ResponseEntity.ok().body(productService.getDepositProductList(productSearch));
    }
}
