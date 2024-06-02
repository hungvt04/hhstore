package com.datn.be.core.admin.amanagediscountperiod.service.impl;

import com.datn.be.core.admin.amanagediscountperiod.model.request.ADiscountPeriodPostRequest;
import com.datn.be.core.admin.amanagediscountperiod.model.request.ADiscountPeriodPutRequest;
import com.datn.be.core.admin.amanagediscountperiod.model.request.ADiscountPeriodSearchRequest;
import com.datn.be.core.admin.amanagediscountperiod.model.response.ADiscountPeriodResponse;
import com.datn.be.core.admin.amanagediscountperiod.repository.ADiscountPeriodRepository;
import com.datn.be.core.admin.amanagediscountperiod.service.ADiscountPeriodService;
import com.datn.be.entity.DiscountPeriod;
import com.datn.be.infrastructure.common.PageResponse;
import com.datn.be.infrastructure.common.ResponseObject;
import com.datn.be.infrastructure.constant.enums.EnumStatusDiscount;
import com.datn.be.infrastructure.constant.enums.EnumTypeDiscount;
import com.datn.be.infrastructure.exception.RestException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ADiscountPeriodServiceImpl implements ADiscountPeriodService {

    private final ADiscountPeriodRepository discountPeriodRepository;

    @Override
    public ResponseObject postDiscountPeriod(ADiscountPeriodPostRequest request) {
        long currentTime = new Date().getTime();
        List<String> errors = new ArrayList<>();
        if (request.getStartDate() < currentTime) {
            errors.add("The start date must be before the current time.");
        }

        if (request.getEndDate() < request.getStartDate()) {
            errors.add("The end date must be before the start date.");
        }

        if (errors.isEmpty()) {
            DiscountPeriod discountPeriod = DiscountPeriod.builder()
                    .name(request.getName())
                    .type(EnumTypeDiscount.valueOf(request.getType()))
                    .status(EnumStatusDiscount.SAP_DIEN_RA)
                    .value(request.getValue())
                    .startDate(request.getStartDate())
                    .endDate(request.getEndDate())
                    .quantity(request.getQuantity())
                    .description(request.getDescription())
                    .build();
            return ResponseObject.builder()
                    .data(discountPeriodRepository.save(discountPeriod))
                    .build();
        }
        throw new RestException(errors);
    }

    @Override
    public ResponseObject putDiscountPeriod(String id, ADiscountPeriodPutRequest request) {
        DiscountPeriod discountPeriod = (DiscountPeriod) this.detailDiscountPeriod(id).getData();

        long currentTime = new Date().getTime();
        if (discountPeriod.getStartDate() < currentTime) {
            // Dang dien ra => Update status
            discountPeriod.setStatus(EnumStatusDiscount.valueOf(request.getStatus()));
            if (currentTime < discountPeriod.getEndDate()) {
                // keo dai thoi gian dien ra
                discountPeriod.setEndDate(request.getEndDate());
            }
            System.out.println("discountPeriod update 74: " + discountPeriod);
            return ResponseObject.builder()
                    .data(discountPeriodRepository.save(discountPeriod))
                    .build();
        }

        // Update tat ca thong tin
        List<String> errors = new ArrayList<>();
        if (request.getStartDate() < currentTime) {
            errors.add("The start date must be before the current time.");
        }

        if (request.getEndDate() < request.getStartDate()) {
            errors.add("The end date must be before the start date.");
        }

        discountPeriod.setName(request.getName());
        discountPeriod.setType(EnumTypeDiscount.valueOf(request.getType()));
        discountPeriod.setStatus(EnumStatusDiscount.valueOf(request.getStatus()));
        discountPeriod.setValue(request.getValue());
        discountPeriod.setStartDate(request.getStartDate());
        discountPeriod.setEndDate(request.getEndDate());
        discountPeriod.setQuantity(request.getQuantity());
        discountPeriod.setDescription(request.getDescription());

        System.out.println("discountPeriod update 99: " + discountPeriod);
        if (errors.isEmpty()) {
            return ResponseObject.builder()
                    .data(discountPeriodRepository.save(discountPeriod))
                    .build();
        }
        throw new RestException(errors);
    }


    @Override
    public ResponseObject deleteDiscountPeriod(String id) {
        DiscountPeriod discountPeriod = (DiscountPeriod) this.detailDiscountPeriod(id).getData();
        discountPeriod.setStatus(EnumStatusDiscount.DA_HUY);
        return ResponseObject.builder()
                .data(discountPeriodRepository.save(discountPeriod))
                .build();
    }

    @Override
    public ResponseObject detailDiscountPeriod(String id) {
        Optional<DiscountPeriod> discountPeriodOptional = discountPeriodRepository.findById(id);
        if (discountPeriodOptional.isEmpty()) {
            throw new RestException(List.of("Not found discount period with id: " + id));
        }

        return ResponseObject.builder()
                .data(discountPeriodOptional.get())
                .build();
    }

    @Override
    public ResponseObject getSearchDiscountPeriod(ADiscountPeriodSearchRequest request) {
        Pageable pageable = PageRequest.of(request.getPage() - 1, request.getSize());
        Page<ADiscountPeriodResponse> page = discountPeriodRepository.getSearchDiscountPeriod(pageable, request);
        PageResponse<ADiscountPeriodResponse> response = new PageResponse<>(page);

        return ResponseObject.builder()
                .data(response)
                .build();
    }
}
