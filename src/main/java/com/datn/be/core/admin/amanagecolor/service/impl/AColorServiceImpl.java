package com.datn.be.core.admin.amanagecolor.service.impl;

import com.datn.be.core.admin.amanagecolor.model.request.AColorPostRequest;
import com.datn.be.core.admin.amanagecolor.model.request.AColorPutRequest;
import com.datn.be.core.admin.amanagecolor.model.request.AColorSearchRequest;
import com.datn.be.core.admin.amanagecolor.model.response.AColorSearchResponse;
import com.datn.be.core.admin.amanagecolor.repository.AColorRepository;
import com.datn.be.core.admin.amanagecolor.service.AColorService;
import com.datn.be.entity.Color;
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
public class AColorServiceImpl implements AColorService {

    private final AColorRepository colorRepository;

    @Override
    public ResponseObject postColor(AColorPostRequest request) {
        boolean isDuplicateCode = colorRepository.existsByCode(request.getCode());
        boolean isDuplicateName = colorRepository.existsByName(request.getName());

        List<String> errors = new ArrayList<>();
        if (isDuplicateCode) {
            errors.add("Code is existed");
        }
        if (isDuplicateName) {
            errors.add("Name is existed");
        }

        if (errors.isEmpty()) {
            Color color = Color.builder()
                    .code(request.getCode())
                    .name(request.getName())
                    .build();
            return ResponseObject.builder()
                    .data(colorRepository.save(color))
                    .build();
        }
        throw new RestException(errors);
    }

    @Override
    public ResponseObject putColor(String id, AColorPutRequest request) {
        Color color = (Color) this.deleteColor(id).getData();

        boolean isDuplicateCode = colorRepository.existsByCode(request.getCode());
        boolean isDuplicateName = colorRepository.existsByName(request.getName());


        List<String> errors = new ArrayList<>();

        if (request.getCode().equalsIgnoreCase(color.getCode())) {
            isDuplicateCode = false;
        }
        if (request.getName().equalsIgnoreCase(color.getName())) {
            isDuplicateName = false;
        }

        if (isDuplicateCode) {
            errors.add("Code is existed");
        }
        if (isDuplicateName) {
            errors.add("Name is existed");
        }

        if (errors.isEmpty()) {
            color.setCode(request.getCode());
            color.setName(request.getName());
            return ResponseObject.builder()
                    .data(colorRepository.save(color))
                    .build();
        }
        throw new RestException(errors);
    }

    @Override
    public ResponseObject deleteColor(String id) {
        Color color = (Color) this.detailColor(id).getData();
        color.setIsDeleted(!color.getIsDeleted());

        return ResponseObject.builder()
                .data(color)
                .build();
    }

    @Override
    public ResponseObject detailColor(String id) {
        Optional<Color> colorOptional = colorRepository.findById(id);
        if (colorOptional.isEmpty()) {
            throw new RestException(List.of("Not found color with id: " + id));
        }

        return ResponseObject.builder()
                .data(colorOptional.get())
                .build();
    }

    @Override
    public ResponseObject getSearchColor(AColorSearchRequest request) {
        Pageable pageable = PageRequest.of(request.getPage() - 1, request.getSize());
        Page<AColorSearchResponse> page = colorRepository.getSearchColor(pageable, request);
        PageResponse<AColorSearchResponse> response = new PageResponse<>(page);

        return ResponseObject.builder()
                .data(response)
                .build();
    }
}
