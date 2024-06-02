package com.datn.be.core.admin.amanageproduct.controller;

import com.datn.be.core.admin.amanageproduct.model.request.AProductPostRequest;
import com.datn.be.core.admin.amanageproduct.model.request.AProductPutRequest;
import com.datn.be.core.admin.amanageproduct.model.request.AProductSearchRequest;
import com.datn.be.core.admin.amanageproduct.service.AProductService;
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
@RequestMapping(BaseUrl.ADMIN_MANAGEMENT_PRODUCT)
public class AProductController {

    private final AProductService productService;

    @PostMapping("/post-product")
    public ResponseEntity<?> postProduct(@Valid @RequestBody AProductPostRequest request) {
        return ResponseEntity.ok(productService.postProduct(request));
    }

    @PutMapping("/put-product/{id}")
    public ResponseEntity<?> putProduct(@PathVariable String id, @Valid @RequestBody AProductPutRequest request) {
        return ResponseEntity.ok(productService.putProduct(id, request));
    }

    @DeleteMapping("/delete-product/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable String id) {
        return ResponseEntity.ok(productService.deletedProduct(id));
    }

    @GetMapping("/detail-product/{id}")
    public ResponseEntity<?> detailProduct(@PathVariable String id) {
        return ResponseEntity.ok(productService.detailProduct(id));
    }

    @GetMapping("/get-search-product")
    public ResponseEntity<?> getSearchProduct(@Param("request") AProductSearchRequest request) {
        return ResponseEntity.ok(productService.getSearchProduct(request));
    }

}
