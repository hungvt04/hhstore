package com.datn.be.core.admin.amanagegender.controller;

import com.datn.be.core.admin.amanagegender.model.request.AGenderPostRequest;
import com.datn.be.core.admin.amanagegender.model.request.AGenderPutRequest;
import com.datn.be.core.admin.amanagegender.model.request.AGenderSearchRequest;
import com.datn.be.core.admin.amanagegender.service.AGenderService;
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
@RequestMapping(BaseUrl.ADMIN_MANAGEMENT_GENDER)
public class AGenderController {

    private final AGenderService genderService;

    @PostMapping("/post-gender")
    public ResponseEntity<?> postGender(@Valid @RequestBody AGenderPostRequest request) {
        return ResponseEntity.ok(genderService.postGender(request));
    }

    @PutMapping("/put-gender/{id}")
    public ResponseEntity<?> putGender(@PathVariable String id, @Valid @RequestBody AGenderPutRequest request) {
        return ResponseEntity.ok(genderService.putGender(id, request));
    }

    @DeleteMapping("/delete-gender/{id}")
    public ResponseEntity<?> deleteGender(@PathVariable String id) {
        return ResponseEntity.ok(genderService.deletedGender(id));
    }

    @GetMapping("/detail-gender/{id}")
    public ResponseEntity<?> detailGender(@PathVariable String id) {
        return ResponseEntity.ok(genderService.detailGender(id));
    }

    @GetMapping("/get-search-gender")
    public ResponseEntity<?> getSearchGender(@Param("request") AGenderSearchRequest request) {
        return ResponseEntity.ok(genderService.getSearchGender(request));
    }

}
