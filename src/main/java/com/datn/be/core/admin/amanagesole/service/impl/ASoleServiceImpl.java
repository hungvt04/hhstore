package com.datn.be.core.admin.amanagesole.service.impl;

import com.datn.be.core.admin.amanagesole.model.request.ASolePostRequest;
import com.datn.be.core.admin.amanagesole.model.request.ASolePutRequest;
import com.datn.be.core.admin.amanagesole.model.request.ASoleSearchRequest;
import com.datn.be.core.admin.amanagesole.model.response.ASoleSearchResponse;
import com.datn.be.core.admin.amanagesole.repository.ASoleRepository;
import com.datn.be.core.admin.amanagesole.service.ASoleService;
import com.datn.be.entity.Sole;
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
public class ASoleServiceImpl implements ASoleService {

    private final ASoleRepository soleRepository;

    @Override
    public ResponseObject postSole(ASolePostRequest request) {
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
            Sole sole = Sole.builder()
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
    public ResponseObject putSole(String id, ASolePutRequest request) {
        Sole sole = (Sole) this.detailSole(id).getData();

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
    public ResponseObject deletedSole(String id) {
        Sole sole = (Sole) this.detailSole(id).getData();
        sole.setIsDeleted(!sole.getIsDeleted());

        return ResponseObject.builder()
                .data(soleRepository.save(sole))
                .build();
    }

    @Override
    public ResponseObject detailSole(String id) {
        Optional<Sole> soleOptional = soleRepository.findById(id);
        if (soleOptional.isEmpty()) {
            throw new RestException(List.of("Not found sole with id: " + id));
        }
        return ResponseObject.builder()
                .data(soleOptional.get())
                .build();
    }

    @Override
    public ResponseObject getSearchSole(ASoleSearchRequest request) {
        Pageable pageable = PageRequest.of(request.getPage() - 1, request.getSize());
        Page<ASoleSearchResponse> page = soleRepository.getSearchSole(pageable, request);
        PageResponse<ASoleSearchResponse> response = new PageResponse<>(page);

        return ResponseObject.builder()
                .data(response)
                .build();
    }
}
