package com.datn.be.core.admin.amanagematerial.controller;

import com.datn.be.core.admin.amanagematerial.model.request.AMaterialPostRequest;
import com.datn.be.core.admin.amanagematerial.model.request.AMaterialPutRequest;
import com.datn.be.core.admin.amanagematerial.model.request.AMaterialSearchRequest;
import com.datn.be.core.admin.amanagematerial.service.AMaterialService;
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
@RequestMapping(BaseUrl.ADMIN_MANAGEMENT_MATERIAL)
public class AMaterialController {

    private final AMaterialService materialService;

    @PostMapping("/post-material")
    public ResponseEntity<?> postMaterial(@Valid @RequestBody AMaterialPostRequest request) {
        return ResponseEntity.ok(materialService.postMaterial(request));
    }

    @PutMapping("/put-material/{id}")
    public ResponseEntity<?> putMaterial(@PathVariable String id, @Valid @RequestBody AMaterialPutRequest request) {
        return ResponseEntity.ok(materialService.putMaterial(id, request));
    }

    @DeleteMapping("/delete-material/{id}")
    public ResponseEntity<?> deleteMaterial(@PathVariable String id) {
        return ResponseEntity.ok(materialService.deletedMaterial(id));
    }

    @GetMapping("/detail-material/{id}")
    public ResponseEntity<?> detailMaterial(@PathVariable String id) {
        return ResponseEntity.ok(materialService.detailMaterial(id));
    }

    @GetMapping("/get-search-material")
    public ResponseEntity<?> getSearchMaterial(@Param("request") AMaterialSearchRequest request) {
        return ResponseEntity.ok(materialService.getSearchMaterial(request));
    }

}
