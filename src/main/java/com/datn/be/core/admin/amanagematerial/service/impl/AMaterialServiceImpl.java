package com.datn.be.core.admin.amanagematerial.service.impl;

import com.datn.be.core.admin.amanagematerial.model.request.AMaterialPostRequest;
import com.datn.be.core.admin.amanagematerial.model.request.AMaterialPutRequest;
import com.datn.be.core.admin.amanagematerial.model.request.AMaterialSearchRequest;
import com.datn.be.core.admin.amanagematerial.model.response.AMaterialSearchResponse;
import com.datn.be.core.admin.amanagematerial.repository.AMaterialRepository;
import com.datn.be.core.admin.amanagematerial.service.AMaterialService;
import com.datn.be.entity.Material;
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
public class AMaterialServiceImpl implements AMaterialService {

    private final AMaterialRepository materialRepository;

    @Override
    public ResponseObject postMaterial(AMaterialPostRequest request) {
        boolean isDuplicateCode = materialRepository.existsByCode(request.getCode());
        boolean isDuplicateName = materialRepository.existsByName(request.getName());

        List<String> errors = new ArrayList<>();

        if (isDuplicateCode) {
            errors.add("Code is existed.");
        }
        if (isDuplicateName) {
            errors.add("Name is existed.");
        }

        if (errors.isEmpty()) {
            Material material = Material.builder()
                    .code(request.getCode())
                    .name(request.getName())
                    .build();

            return ResponseObject.builder()
                    .data(materialRepository.save(material))
                    .build();
        }

        throw new RestException(errors);
    }

    @Override
    public ResponseObject putMaterial(String id, AMaterialPutRequest request) {
        Material material = (Material) this.detailMaterial(id).getData();

        boolean isDuplicateCode = materialRepository.existsByCode(request.getCode());
        boolean isDuplicateName = materialRepository.existsByName(request.getName());

        if (request.getCode().equalsIgnoreCase(material.getCode())) {
            isDuplicateCode = false;
        }
        if (request.getName().equalsIgnoreCase(material.getName())) {
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
            material.setCode(request.getCode());
            material.setName(request.getName());

            return ResponseObject.builder()
                    .data(materialRepository.save(material))
                    .build();
        }

        throw new RestException(errors);
    }

    @Override
    public ResponseObject deletedMaterial(String id) {
        Material material = (Material) this.detailMaterial(id).getData();
        material.setIsDeleted(!material.getIsDeleted());

        return ResponseObject.builder()
                .data(materialRepository.save(material))
                .build();
    }

    @Override
    public ResponseObject detailMaterial(String id) {
        Optional<Material> materialOptional = materialRepository.findById(id);
        if (materialOptional.isEmpty()) {
            throw new RestException(List.of("Not found material with id: " + id));
        }
        return ResponseObject.builder()
                .data(materialOptional.get())
                .build();
    }

    @Override
    public ResponseObject getSearchMaterial(AMaterialSearchRequest request) {
        Pageable pageable = PageRequest.of(request.getPage() - 1, request.getSize());
        Page<AMaterialSearchResponse> page = materialRepository.getSearchMaterial(pageable, request);
        PageResponse<AMaterialSearchResponse> response = new PageResponse<>(page);

        return ResponseObject.builder()
                .data(response)
                .build();
    }
}
