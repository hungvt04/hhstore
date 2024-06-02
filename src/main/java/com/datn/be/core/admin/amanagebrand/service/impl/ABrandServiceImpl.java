package com.datn.be.core.admin.amanagebrand.service.impl;

import com.datn.be.core.admin.amanagebrand.model.request.ABrandPostRequest;
import com.datn.be.core.admin.amanagebrand.model.request.ABrandPutRequest;
import com.datn.be.core.admin.amanagebrand.model.request.ABrandSearchRequest;
import com.datn.be.core.admin.amanagebrand.model.response.ABrandSearchResponse;
import com.datn.be.core.admin.amanagebrand.repository.ABrandRepository;
import com.datn.be.core.admin.amanagebrand.service.ABrandService;
import com.datn.be.entity.Brand;
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
public class ABrandServiceImpl implements ABrandService {

    private final ABrandRepository brandRepository;

    @Override
    public ResponseObject detailBrand(String id) {
        Optional<Brand> brandOptional = brandRepository.findById(id);
        if (brandOptional.isEmpty()) {
            throw new RestException(List.of("Not found with brand id: " + id));
        }
        return new ResponseObject(brandOptional.get());
    }

    @Override
    public ResponseObject getSearchBrand(ABrandSearchRequest request) {
        Pageable pageable = PageRequest.of(request.getPage() - 1, request.getSize());
        Page<ABrandSearchResponse> page = brandRepository.getSearchBrand(pageable, request);
        PageResponse<ABrandSearchResponse> response = new PageResponse<>(page);
        return ResponseObject.builder()
                .data(response)
                .build();
    }

    @Override
    public ResponseObject postBrand(ABrandPostRequest request) {
        boolean isDuplicateCode = brandRepository.existsByCode(request.getCode());
        boolean isDuplicateName = brandRepository.existsByName(request.getName());
        List<String> errors = new ArrayList<>();

        if (isDuplicateCode) {
            errors.add("Code is existed");
        }
        if (isDuplicateName) {
            errors.add("Name is existed");
        }

        if (errors.isEmpty()) {
            Brand brand = new Brand(request.getCode(), request.getName());
            return ResponseObject.builder()
                    .data(brandRepository.save(brand))
                    .build();
        }

        throw new RestException(errors);
    }

    @Override
    public ResponseObject putBrand(String id, ABrandPutRequest request) {
        Optional<Brand> brandOptional = brandRepository.findById(id);
        if (brandOptional.isEmpty()) {
            throw new RestException(List.of("Not found with brand id: " + id));
        }

        Brand brandUpdate = brandOptional.get();
        boolean isDuplicateCode = brandRepository.existsByCode(request.getCode());
        boolean isDuplicateName = brandRepository.existsByName(request.getName());

        if (request.getCode().equalsIgnoreCase(brandUpdate.getCode())) {
            isDuplicateCode = false;
        }
        if (request.getName().equalsIgnoreCase(brandUpdate.getName())) {
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
            brandUpdate.setCode(request.getCode());
            brandUpdate.setName(request.getName());
            return ResponseObject.builder()
                    .data(brandRepository.save(brandUpdate))
                    .build();
        }

        return null;
    }

    @Override
    public ResponseObject deleteBrand(String id) {
        Optional<Brand> brandOptional = brandRepository.findById(id);
        if (brandOptional.isEmpty()) {
            throw new RestException(List.of("Not found with brand id: " + id));
        }

        Brand brandDelete = brandOptional.get();
        brandDelete.setIsDeleted(!brandDelete.getIsDeleted());
        return ResponseObject.builder()
                .data(brandRepository.save(brandDelete))
                .build();
    }

}
