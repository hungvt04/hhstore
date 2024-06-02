package com.datn.be.core.admin.amanagegender.service.impl;

import com.datn.be.core.admin.amanagegender.model.request.AGenderPostRequest;
import com.datn.be.core.admin.amanagegender.model.request.AGenderPutRequest;
import com.datn.be.core.admin.amanagegender.model.request.AGenderSearchRequest;
import com.datn.be.core.admin.amanagegender.model.response.AGenderSearchResponse;
import com.datn.be.core.admin.amanagegender.repository.AGenderRepository;
import com.datn.be.core.admin.amanagegender.service.AGenderService;
import com.datn.be.entity.Gender;
import com.datn.be.infrastructure.common.PageResponse;
import com.datn.be.infrastructure.common.ResponseObject;
import com.datn.be.infrastructure.exception.RestException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AGenderServiceImpl implements AGenderService {

    private final AGenderRepository soleRepository;

    @Override
    public ResponseObject postGender(AGenderPostRequest request) {
        boolean isDuplicateCode = soleRepository.existsByCode(request.getCode());
        boolean isDuplicateName = soleRepository.existsByName(request.getName());

        List<String> errors = new ArrayList<>();

        if (isDuplicateCode) {
            errors.add("Code is existed.");
        }
        if (isDuplicateName) {
            errors.add("Name is existed.");
        }

        if (errors.isEmpty()) {
            Gender sole = Gender.builder()
                    .code(request.getCode())
                    .name(request.getName())
                    .build();

            return ResponseObject.builder()
                    .data(soleRepository.save(sole))
                    .build();
        }

        throw new RestException(errors);
    }

    @Override
    public ResponseObject putGender(String id, AGenderPutRequest request) {
        Gender sole = (Gender) this.detailGender(id).getData();

        boolean isDuplicateCode = soleRepository.existsByCode(request.getCode());
        boolean isDuplicateName = soleRepository.existsByName(request.getName());

        if (request.getCode().equalsIgnoreCase(sole.getCode())) {
            isDuplicateCode = false;
        }
        if (request.getName().equalsIgnoreCase(sole.getName())) {
            isDuplicateName = false;
        }

        List<String> errors = new ArrayList<>();

        if (isDuplicateCode) {
            errors.add("Code is existed.");
        }
        if (isDuplicateName) {
            errors.add("Name is existed.");
        }

        if (errors.isEmpty()) {
            sole.setCode(request.getCode());
            sole.setName(request.getName());

            return ResponseObject.builder()
                    .data(soleRepository.save(sole))
                    .build();
        }

        throw new RestException(errors);
    }

    @Override
    public ResponseObject deletedGender(String id) {
        Gender sole = (Gender) this.detailGender(id).getData();
        sole.setIsDeleted(!sole.getIsDeleted());

        return ResponseObject.builder()
                .data(soleRepository.save(sole))
                .build();
    }

    @Override
    public ResponseObject detailGender(String id) {
        Optional<Gender> soleOptional = soleRepository.findById(id);
        if (soleOptional.isEmpty()) {
            throw new RestException(List.of("Not found sole with id: " + id));
        }
        return ResponseObject.builder()
                .data(soleOptional.get())
                .build();
    }

    @Override
    public ResponseObject getSearchGender(AGenderSearchRequest request) {
        Pageable pageable = PageRequest.of(request.getPage() - 1, request.getSize());
        Page<AGenderSearchResponse> page = soleRepository.getSearchGender(pageable, request);
        PageResponse<AGenderSearchResponse> response = new PageResponse<>(page);

        return ResponseObject.builder()
                .data(response)
                .build();
    }
}
