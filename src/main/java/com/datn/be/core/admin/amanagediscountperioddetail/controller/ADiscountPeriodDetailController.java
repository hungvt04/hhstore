package com.datn.be.core.admin.amanagediscountperioddetail.controller;

import com.datn.be.core.admin.amanagediscountperioddetail.model.request.ADiscountPeriodDetailPostRequest;
import com.datn.be.core.admin.amanagediscountperioddetail.model.request.ADiscountPeriodDetailPutRequest;
import com.datn.be.core.admin.amanagediscountperioddetail.model.request.ADiscountPeriodDetailSearchRequest;
import com.datn.be.core.admin.amanagediscountperioddetail.service.ADiscountPeriodDetailService;
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
@RequestMapping(BaseUrl.ADMIN_MANAGEMENT_DISCOUNT_PERIOD_DETAIL)
public class ADiscountPeriodDetailController {

    private final ADiscountPeriodDetailService discountPeriodDetailService;

    @PostMapping("/post-discount-period-detail")
    public ResponseEntity<?> postDiscountPeriodDetail(@Valid @RequestBody ADiscountPeriodDetailPostRequest request) {
        return ResponseEntity.ok(discountPeriodDetailService.postDiscountPeriodDetail(request));
    }

    @PutMapping("/put-discount-period-detail/{id}")
    public ResponseEntity<?> putDiscountPeriodDetail(@PathVariable String id, @Valid @RequestBody ADiscountPeriodDetailPutRequest request) {
        return ResponseEntity.ok(discountPeriodDetailService.putDiscountPeriodDetail(id, request));
    }

    @DeleteMapping("/delete-discount-period-detail/{id}")
    public ResponseEntity<?> deleteDiscountPeriodDetail(@PathVariable String id) {
        return ResponseEntity.ok(discountPeriodDetailService.deletedDiscountPeriodDetail(id));
    }

    @GetMapping("/detail-discount-period-detail/{id}")
    public ResponseEntity<?> detailDiscountPeriodDetail(@PathVariable String id) {
        return ResponseEntity.ok(discountPeriodDetailService.detailDiscountPeriodDetail(id));
    }

    @GetMapping("/get-search-discount-period-detail")
    public ResponseEntity<?> getSearchDiscountPeriodDetail(@Param("request") ADiscountPeriodDetailSearchRequest request) {
        return ResponseEntity.ok(discountPeriodDetailService.getSearchDiscountPeriodDetail(request));
    }

}
