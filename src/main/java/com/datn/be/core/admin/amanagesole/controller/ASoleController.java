package com.datn.be.core.admin.amanagesole.controller;

import com.datn.be.core.admin.amanagesole.model.request.ASolePostRequest;
import com.datn.be.core.admin.amanagesole.model.request.ASolePutRequest;
import com.datn.be.core.admin.amanagesole.model.request.ASoleSearchRequest;
import com.datn.be.core.admin.amanagesole.service.ASoleService;
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
@RequestMapping(BaseUrl.ADMIN_MANAGEMENT_SOLE)
public class ASoleController {

    private final ASoleService soleService;

    @PostMapping("/post-sole")
    public ResponseEntity<?> postSole(@Valid @RequestBody ASolePostRequest request) {
        return ResponseEntity.ok(soleService.postSole(request));
    }

    @PutMapping("/put-sole/{id}")
    public ResponseEntity<?> putSole(@PathVariable String id, @Valid @RequestBody ASolePutRequest request) {
        return ResponseEntity.ok(soleService.putSole(id, request));
    }

    @DeleteMapping("/delete-sole/{id}")
    public ResponseEntity<?> deleteSole(@PathVariable String id) {
        return ResponseEntity.ok(soleService.deletedSole(id));
    }

    @GetMapping("/detail-sole/{id}")
    public ResponseEntity<?> detailSole(@PathVariable String id) {
        return ResponseEntity.ok(soleService.detailSole(id));
    }

    @GetMapping("/get-search-sole")
    public ResponseEntity<?> getSearchSole(@Param("request") ASoleSearchRequest request) {
        return ResponseEntity.ok(soleService.getSearchSole(request));
    }

}
