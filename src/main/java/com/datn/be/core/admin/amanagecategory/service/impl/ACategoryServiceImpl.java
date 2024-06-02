package com.datn.be.core.admin.amanagecategory.service.impl;

import com.datn.be.core.admin.amanagecategory.model.request.ACategoryPostRequest;
import com.datn.be.core.admin.amanagecategory.model.request.ACategoryPutRequest;
import com.datn.be.core.admin.amanagecategory.model.request.ACategorySearchRequest;
import com.datn.be.core.admin.amanagecategory.model.response.ACategorySearchResponse;
import com.datn.be.core.admin.amanagecategory.repository.ACategoryRepository;
import com.datn.be.core.admin.amanagecategory.service.ACategoryService;
import com.datn.be.entity.Category;
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
public class ACategoryServiceImpl implements ACategoryService {

    private final ACategoryRepository categoryRepository;

    @Override
    public ResponseObject postCategory(ACategoryPostRequest request) {
        System.out.println(request);
        boolean isDuplicateCode = categoryRepository.existsByCode(request.getCode());
        boolean isDuplicateName = categoryRepository.existsByName(request.getName());
        List<String> errors = new ArrayList<>();
        if (isDuplicateCode) {
            errors.add("Code is existed");
        }
        if (isDuplicateName) {
            errors.add("Name is existed");
        }

        if (errors.isEmpty()) {
            Category category = Category.builder()
                    .code(request.getCode())
                    .name(request.getName())
                    .build();
            return ResponseObject.builder()
                    .data(categoryRepository.save(category))
                    .build();
        }
        throw new RestException(errors);
    }

    @Override
    public ResponseObject putCategory(String id, ACategoryPutRequest request) {
        Optional<Category> categoryOptional = categoryRepository.findById(id);
        if (categoryOptional.isEmpty()) {
            throw new RestException(List.of("Not found category with id: " + id));
        }
        Category category = categoryOptional.get();

        boolean isDuplicateCode = categoryRepository.existsByCode(request.getCode());
        boolean isDuplicateName = categoryRepository.existsByName(request.getName());

        if (category.getCode().equalsIgnoreCase(request.getCode())) {
            isDuplicateCode = false;
        }
        if (category.getName().equalsIgnoreCase(request.getName())) {
            isDuplicateName = false;
        }

        List<String> errors = new ArrayList<>();
        if (isDuplicateCode) {
            errors.add("Code is existed");
        }
        if (isDuplicateName) {
            errors.add("Name is existed");
        }

        if (errors.isEmpty()) {
            category.setCode(request.getCode());
            category.setName(request.getName());

            return ResponseObject.builder()
                    .data(categoryRepository.save(category))
                    .build();
        }
        throw new RestException(errors);
    }

    @Override
    public ResponseObject deleteCategory(String id) {
        Optional<Category> categoryOptional = categoryRepository.findById(id);
        if (categoryOptional.isEmpty()) {
            throw new RestException(List.of("Not found category with id: " + id));
        }
        Category category = categoryOptional.get();
        category.setIsDeleted(!category.getIsDeleted());

        return ResponseObject.builder()
                .data(categoryRepository.save(category))
                .build();
    }

    @Override
    public ResponseObject detailCategory(String id) {
        Optional<Category> categoryOptional = categoryRepository.findById(id);
        if (categoryOptional.isEmpty()) {
            throw new RestException(List.of("Not found category with id: " + id));
        }

        return ResponseObject.builder()
                .data(categoryOptional.get())
                .build();
    }

    @Override
    public ResponseObject getSearchCategory(ACategorySearchRequest request) {
        Pageable pageable = PageRequest.of(request.getPage() - 1, request.getSize());
        Page<ACategorySearchResponse> page = categoryRepository.getSearchCategory(pageable, request);
        PageResponse<ACategorySearchResponse> response = new PageResponse<>(page);
        return ResponseObject.builder()
                .data(response)
                .build();
    }
}
