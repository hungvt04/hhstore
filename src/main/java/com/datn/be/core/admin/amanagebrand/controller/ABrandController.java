package com.datn.be.core.admin.amanagebrand.controller;

import com.datn.be.core.admin.amanagebrand.model.request.ABrandPostRequest;
import com.datn.be.core.admin.amanagebrand.model.request.ABrandPutRequest;
import com.datn.be.core.admin.amanagebrand.model.request.ABrandSearchRequest;
import com.datn.be.core.admin.amanagebrand.service.ABrandService;
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
@RequestMapping(value = BaseUrl.ADMIN_MANAGEMENT_BRAND)
@RequiredArgsConstructor
@CrossOrigin("*")
public class ABrandController {

    private final ABrandService brandService;

    @PostMapping("/post-brand")
    public ResponseEntity<?> postBrand(@RequestBody @Valid ABrandPostRequest request) {
        return ResponseEntity.ok(brandService.postBrand(request));
    }

    @PutMapping("/put-brand/{id}")
    public ResponseEntity<?> putBrand(@Valid @PathVariable String id, @RequestBody ABrandPutRequest request) {
        return ResponseEntity.ok(brandService.putBrand(id, request));
    }

    @DeleteMapping("/delete-brand/{id}")
    public ResponseEntity<?> deleteBrand(@PathVariable String id) {
        return ResponseEntity.ok(brandService.deleteBrand(id));
    }

    @GetMapping("/detail-brand/{id}")
    public ResponseEntity<?> detailBrand(@PathVariable String id) {
        return ResponseEntity.ok(brandService.detailBrand(id));
    }

    @GetMapping("/get-search-brand")
    public ResponseEntity<?> getSearchBrand(@Param("request") ABrandSearchRequest request) {
        System.out.println(request);
        return ResponseEntity.ok(brandService.getSearchBrand(request));
    }

}
