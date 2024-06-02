package com.datn.be.core.admin.amanagediscountperioddetail.service.impl;

import com.datn.be.core.admin.amanagediscountperioddetail.model.request.ADiscountPeriodDetailPostRequest;
import com.datn.be.core.admin.amanagediscountperioddetail.model.request.ADiscountPeriodDetailPutRequest;
import com.datn.be.core.admin.amanagediscountperioddetail.model.request.ADiscountPeriodDetailSearchRequest;
import com.datn.be.core.admin.amanagediscountperioddetail.repository.ADiscountPeriodDetailRepository;
import com.datn.be.core.admin.amanagediscountperioddetail.service.ADiscountPeriodDetailService;
import com.datn.be.entity.DiscountPeriod;
import com.datn.be.entity.DiscountPeriodDetail;
import com.datn.be.entity.ProductDetail;
import com.datn.be.infrastructure.common.ResponseObject;
import com.datn.be.infrastructure.exception.RestException;
import com.datn.be.repository.DiscountPeriodRepository;
import com.datn.be.repository.ProductDetailRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ADiscountPeriodDetailServiceImpl implements ADiscountPeriodDetailService {

    private final ProductDetailRepository productDetailRepository;

    private final DiscountPeriodRepository discountPeriodRepository;

    private final ADiscountPeriodDetailRepository discountPeriodDetailRepository;

    @Transactional
    @Override
    public ResponseObject postDiscountPeriodDetail(ADiscountPeriodDetailPostRequest request) {
        List<String> errors = new ArrayList<>();

        Optional<DiscountPeriod> discountPeriodOptional = discountPeriodRepository.findById(request.getDiscountPeriodId());

        DiscountPeriodDetail discountPeriodDetail = new DiscountPeriodDetail();
        if (discountPeriodOptional.isEmpty()) {
            throw new RestException(List.of("Not found discount period with id: " + request.getProductDetailId()));
        } else {
            discountPeriodDetail.setDiscountPeriod(discountPeriodOptional.get());
        }

        for (String idProductDetail : request.getProductDetailId()) {
            Optional<ProductDetail> productDetailOptional = productDetailRepository.findById(idProductDetail);
            if (productDetailOptional.isEmpty()) {
                errors.add("Not found product detail with id: " + request.getProductDetailId());
            }
        }

        if (!errors.isEmpty()) {
            throw new RestException(errors);
        }


        for (String idProductDetail : request.getProductDetailId()) {
            Optional<ProductDetail> productDetailOptional = productDetailRepository.findById(idProductDetail);
            discountPeriodDetail.setProductDetail(productDetailOptional.get());
        }

        return ResponseObject.builder()
                .data(discountPeriodDetailRepository.save(discountPeriodDetail))
                .build();
    }

    @Override
    public ResponseObject putDiscountPeriodDetail(String id, ADiscountPeriodDetailPutRequest request) {
        return null;
    }

    @Override
    public ResponseObject deletedDiscountPeriodDetail(String id) {
        return null;
    }

    @Override
    public ResponseObject detailDiscountPeriodDetail(String id) {
        return null;
    }

    @Override
    public ResponseObject getSearchDiscountPeriodDetail(ADiscountPeriodDetailSearchRequest request) {
        return null;
    }

}
