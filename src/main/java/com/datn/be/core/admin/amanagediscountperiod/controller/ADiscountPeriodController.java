package com.datn.be.core.admin.amanagediscountperiod.controller;

import com.datn.be.core.admin.amanagediscountperiod.model.request.ADiscountPeriodPostRequest;
import com.datn.be.core.admin.amanagediscountperiod.model.request.ADiscountPeriodPutRequest;
import com.datn.be.core.admin.amanagediscountperiod.model.request.ADiscountPeriodSearchRequest;
import com.datn.be.core.admin.amanagediscountperiod.service.ADiscountPeriodService;
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
@RequestMapping(BaseUrl.ADMIN_MANAGEMENT_DISCOUNT_PERIOD)
public class ADiscountPeriodController {

    private final ADiscountPeriodService discountPeriodService;

    @PostMapping("/post-discount-period")
    public ResponseEntity<?> postDiscountPeriod(@Valid @RequestBody ADiscountPeriodPostRequest request) {
        return ResponseEntity.ok(discountPeriodService.postDiscountPeriod(request));
    }

    @PutMapping("/put-discount-period/{id}")
    public ResponseEntity<?> putDiscountPeriod(@PathVariable String id, @Valid @RequestBody ADiscountPeriodPutRequest request) {
        return ResponseEntity.ok(discountPeriodService.putDiscountPeriod(id, request));
    }

    @DeleteMapping("/delete-discount-period/{id}")
    public ResponseEntity<?> deleteDiscountPeriod(@PathVariable String id) {
        return ResponseEntity.ok(discountPeriodService.deleteDiscountPeriod(id));
    }

    @GetMapping("/detail-discount-period/{id}")
    public ResponseEntity<?> detailDiscountPeriod(@PathVariable String id) {
        return ResponseEntity.ok(discountPeriodService.detailDiscountPeriod(id));
    }

    @GetMapping("/get-search-discount-period")
    public ResponseEntity<?> getSearchDiscountPeriod(@Param("request") ADiscountPeriodSearchRequest request) {
        return ResponseEntity.ok(discountPeriodService.getSearchDiscountPeriod(request));
    }

}
