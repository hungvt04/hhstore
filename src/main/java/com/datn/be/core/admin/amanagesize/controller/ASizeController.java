package com.datn.be.core.admin.amanagesize.controller;

import com.datn.be.core.admin.amanagesize.model.request.ASizePostRequest;
import com.datn.be.core.admin.amanagesize.model.request.ASizePutRequest;
import com.datn.be.core.admin.amanagesize.model.request.ASizeSearchRequest;
import com.datn.be.core.admin.amanagesize.service.ASizeService;
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
@RequestMapping(BaseUrl.ADMIN_MANAGEMENT_SIZE)
public class ASizeController {

    private final ASizeService sizeService;

    @PostMapping("/post-size")
    public ResponseEntity<?> postSize(@Valid @RequestBody ASizePostRequest request) {
        return ResponseEntity.ok(sizeService.postSize(request));
    }

    @PutMapping("/put-size/{id}")
    public ResponseEntity<?> putSize(@PathVariable String id, @Valid @RequestBody ASizePutRequest request) {
        return ResponseEntity.ok(sizeService.putSize(id, request));
    }

    @DeleteMapping("/delete-size/{id}")
    public ResponseEntity<?> deleteSize(@PathVariable String id) {
        return ResponseEntity.ok(sizeService.deletedSize(id));
    }

    @GetMapping("/detail-size/{id}")
    public ResponseEntity<?> detailSize(@PathVariable String id) {
        return ResponseEntity.ok(sizeService.detailSize(id));
    }

    @GetMapping("/get-search-size")
    public ResponseEntity<?> getSearchSize(@Param("request") ASizeSearchRequest request) {
        return ResponseEntity.ok(sizeService.getSearchSize(request));
    }

}
