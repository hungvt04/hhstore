package com.datn.be.core.admin.amanagesize.service.impl;

import com.datn.be.core.admin.amanagesize.model.request.ASizePostRequest;
import com.datn.be.core.admin.amanagesize.model.request.ASizePutRequest;
import com.datn.be.core.admin.amanagesize.model.request.ASizeSearchRequest;
import com.datn.be.core.admin.amanagesize.model.response.ASizeSearchResponse;
import com.datn.be.core.admin.amanagesize.repository.ASizeRepository;
import com.datn.be.core.admin.amanagesize.service.ASizeService;
import com.datn.be.entity.Size;
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
public class ASizeServiceImpl implements ASizeService {

    private final ASizeRepository sizeRepository;

    @Override
    public ResponseObject postSize(ASizePostRequest request) {
        boolean isDuplicateCode = sizeRepository.existsByCode(request.getCode());
        boolean isDuplicateName = sizeRepository.existsByName(request.getName());

        List<String> errors = new ArrayList<>();

        if (isDuplicateCode) {
            errors.add("Code is existed.");
        }
        if (isDuplicateName) {
            errors.add("Name is existed.");
        }

        if (errors.isEmpty()) {
            Size size = Size.builder()
                    .code(request.getCode())
                    .name(request.getName())
                    .build();

            return ResponseObject.builder()
                    .data(sizeRepository.save(size))
                    .build();
        }

        throw new RestException(errors);
    }

    @Override
    public ResponseObject putSize(String id, ASizePutRequest request) {
        Size size = (Size) this.detailSize(id).getData();

        boolean isDuplicateCode = sizeRepository.existsByCode(request.getCode());
        boolean isDuplicateName = sizeRepository.existsByName(request.getName());

        if (request.getCode().equalsIgnoreCase(size.getCode())) {
            isDuplicateCode = false;
        }
        if (request.getName().equalsIgnoreCase(size.getName())) {
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
            size.setCode(request.getCode());
            size.setName(request.getName());

            return ResponseObject.builder()
                    .data(sizeRepository.save(size))
                    .build();
        }

        throw new RestException(errors);
    }

    @Override
    public ResponseObject deletedSize(String id) {
        Size size = (Size) this.detailSize(id).getData();
        size.setIsDeleted(!size.getIsDeleted());

        return ResponseObject.builder()
                .data(sizeRepository.save(size))
                .build();
    }

    @Override
    public ResponseObject detailSize(String id) {
        Optional<Size> sizeOptional = sizeRepository.findById(id);
        if (sizeOptional.isEmpty()) {
            throw new RestException(List.of("Not found size with id: " + id));
        }
        return ResponseObject.builder()
                .data(sizeOptional.get())
                .build();
    }

    @Override
    public ResponseObject getSearchSize(ASizeSearchRequest request) {
        Pageable pageable = PageRequest.of(request.getPage() - 1, request.getSize());
        Page<ASizeSearchResponse> page = sizeRepository.getSearchSize(pageable, request);
        PageResponse<ASizeSearchResponse> response = new PageResponse<>(page);

        return ResponseObject.builder()
                .data(response)
                .build();
    }
}
