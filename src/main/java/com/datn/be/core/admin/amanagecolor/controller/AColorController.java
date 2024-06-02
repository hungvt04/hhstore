package com.datn.be.core.admin.amanagecolor.controller;

import com.datn.be.core.admin.amanagecolor.model.request.AColorPostRequest;
import com.datn.be.core.admin.amanagecolor.model.request.AColorPutRequest;
import com.datn.be.core.admin.amanagecolor.model.request.AColorSearchRequest;
import com.datn.be.core.admin.amanagecolor.service.AColorService;
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
@RequiredArgsConstructor
@CrossOrigin("*")
@RequestMapping(BaseUrl.ADMIN_MANAGEMENT_COLOR)
public class AColorController {

    private final AColorService colorService;

    @PostMapping("/post-color")
    public ResponseEntity<?> postColor(@Valid @RequestBody AColorPostRequest request) {
        return ResponseEntity.ok(colorService.postColor(request));
    }

    @PutMapping("/put-color/{id}")
    public ResponseEntity<?> putColor(@PathVariable String id, @Valid @RequestBody AColorPutRequest request) {
        return ResponseEntity.ok(colorService.putColor(id, request));
    }

    @DeleteMapping("/delete-color/{id}")
    public ResponseEntity<?> deletedColor(@PathVariable String id) {
        return ResponseEntity.ok(colorService.deleteColor(id));
    }

    @GetMapping("/detail-color/{id}")
    public ResponseEntity<?> detailColor(@PathVariable String id) {
        return ResponseEntity.ok(colorService.detailColor(id));
    }

    @GetMapping("/get-search-color")
    public ResponseEntity<?> getSearchColor(@Param("request") AColorSearchRequest request) {
        return ResponseEntity.ok(colorService.getSearchColor(request));
    }

}
