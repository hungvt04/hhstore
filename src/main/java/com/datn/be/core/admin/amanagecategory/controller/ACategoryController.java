package com.datn.be.core.admin.amanagecategory.controller;

import com.datn.be.core.admin.amanagecategory.model.request.ACategoryPostRequest;
import com.datn.be.core.admin.amanagecategory.model.request.ACategoryPutRequest;
import com.datn.be.core.admin.amanagecategory.model.request.ACategorySearchRequest;
import com.datn.be.core.admin.amanagecategory.service.ACategoryService;
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
@RequestMapping(BaseUrl.ADMIN_MANAGEMENT_CATEGORY)
public class ACategoryController {

    private final ACategoryService categoryService;

    @PostMapping("/post-category")
    public ResponseEntity<?> postCategory(@Valid @RequestBody ACategoryPostRequest request) {
        return ResponseEntity.ok(categoryService.postCategory(request));
    }

    @PutMapping("/put-category/{id}")
    public ResponseEntity<?> putCategory(@PathVariable String id, @Valid @RequestBody ACategoryPutRequest request) {
        return ResponseEntity.ok(categoryService.putCategory(id, request));
    }

    @DeleteMapping("/delete-category/{id}")
    public ResponseEntity<?> deleteCategory(@PathVariable String id) {
        return ResponseEntity.ok(categoryService.deleteCategory(id));
    }

    @GetMapping("/detail-category/{id}")
    public ResponseEntity<?> detailCategory(@PathVariable String id) {
        return ResponseEntity.ok(categoryService.detailCategory(id));
    }

    @GetMapping("/get-search-category")
    public ResponseEntity<?> getSearchCategory(@Param("request") ACategorySearchRequest request) {
        return ResponseEntity.ok(categoryService.getSearchCategory(request));
    }

}
