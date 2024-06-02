package com.datn.be.core.admin.amanageproductdetail.controller;

import com.datn.be.core.admin.amanageproductdetail.model.request.AProductDetailPostRequest;
import com.datn.be.core.admin.amanageproductdetail.model.request.AProductDetailPutRequest;
import com.datn.be.core.admin.amanageproductdetail.model.request.AProductDetailSearchRequest;
import com.datn.be.core.admin.amanageproductdetail.service.AProductDetailService;
import com.datn.be.infrastructure.constant.BaseUrl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("*")
@RequiredArgsConstructor
@RequestMapping(BaseUrl.ADMIN_MANAGEMENT_PRODUCT_DETAIL)
public class AProductDetailController {

    private final AProductDetailService productDetailService;

    @PostMapping("/post-product-detail")
    public ResponseEntity<?> postProductDetail(@Valid @RequestBody AProductDetailPostRequest request) {
        return ResponseEntity.ok(productDetailService.postProductDetail(request));
    }

    @PutMapping("/put-product-detail/{id}")
    public ResponseEntity<?> putProductDetail(@PathVariable String id, @Valid @RequestBody AProductDetailPutRequest request) {
        return ResponseEntity.ok(productDetailService.putProductDetail(id, request));
    }

    @DeleteMapping("/delete-product-detail/{id}")
    public ResponseEntity<?> deleteProductDetail(@PathVariable String id) {
        return ResponseEntity.ok(productDetailService.deletedProductDetail(id));
    }

    @GetMapping("/detail-product-detail/{id}")
    public ResponseEntity<?> detailProductDetail(@PathVariable String id) {
        return ResponseEntity.ok(productDetailService.detailProductDetail(id));
    }

    @GetMapping("/get-search-product-detail")
    public ResponseEntity<?> getSearchProductDetail(@Param("request") AProductDetailSearchRequest request) {
        return ResponseEntity.ok(productDetailService.getSearchProductDetail(request));
    }

}
