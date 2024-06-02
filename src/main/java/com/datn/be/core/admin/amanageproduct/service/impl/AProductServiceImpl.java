package com.datn.be.core.admin.amanageproduct.service.impl;

import com.datn.be.core.admin.amanageproduct.model.request.AProductPostRequest;
import com.datn.be.core.admin.amanageproduct.model.request.AProductPutRequest;
import com.datn.be.core.admin.amanageproduct.model.request.AProductSearchRequest;
import com.datn.be.core.admin.amanageproduct.model.response.AProductSearchResponse;
import com.datn.be.core.admin.amanageproduct.repository.AProductRepository;
import com.datn.be.core.admin.amanageproduct.service.AProductService;
import com.datn.be.entity.Brand;
import com.datn.be.entity.Category;
import com.datn.be.entity.Gender;
import com.datn.be.entity.Material;
import com.datn.be.entity.Product;
import com.datn.be.entity.Sole;
import com.datn.be.infrastructure.common.PageResponse;
import com.datn.be.infrastructure.common.ResponseObject;
import com.datn.be.infrastructure.exception.RestException;
import com.datn.be.repository.BrandRepository;
import com.datn.be.repository.CategoryRepository;
import com.datn.be.repository.GenderRepository;
import com.datn.be.repository.MaterialRepository;
import com.datn.be.repository.SoleRepository;
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
public class AProductServiceImpl implements AProductService {

    private final BrandRepository brandRepository;

    private final CategoryRepository categoryRepository;

    private final MaterialRepository materialRepository;

    private final SoleRepository soleRepository;

    private final GenderRepository genderRepository;

    private final AProductRepository productRepository;

    @Override
    public ResponseObject postProduct(AProductPostRequest request) {
        List<String> errors = new ArrayList<>();

        Optional<Brand> brandOptional = brandRepository.findById(request.getIdBrand());
        Optional<Category> categoryOptional = categoryRepository.findById(request.getIdCategory());
        Optional<Material> materialOptional = materialRepository.findById(request.getIdMaterial());
        Optional<Sole> soleOptional = soleRepository.findById(request.getIdSole());
        Optional<Gender> genderOptional = genderRepository.findById(request.getIdGender());

        if (brandOptional.isEmpty()) {
            errors.add("Not found brand with id: " + request.getIdBrand());
        }
        if (categoryOptional.isEmpty()) {
            errors.add("Not found category with id: " + request.getIdCategory());
        }
        if (materialOptional.isEmpty()) {
            errors.add("Not found material with id: " + request.getIdMaterial());
        }
        if (soleOptional.isEmpty()) {
            errors.add("Not found sole with id: " + request.getIdSole());
        }
        if (genderOptional.isEmpty()) {
            errors.add("Not found gender with id: " + request.getIdGender());
        }

        if (errors.isEmpty()) {
            Product product = Product.builder()
                    .name(request.getName())
                    .brand(brandOptional.get())
                    .category(categoryOptional.get())
                    .gender(genderOptional.get())
                    .material(materialOptional.get())
                    .sole(soleOptional.get())
                    .description(request.getDescription())
                    .build();

            return ResponseObject.builder()
                    .data(productRepository.save(product))
                    .build();
        }

        throw new RestException(errors);
    }

    @Override
    public ResponseObject putProduct(String id, AProductPutRequest request) {
        Product product = (Product) this.detailProduct(id).getData();
        List<String> errors = new ArrayList<>();

        Optional<Brand> brandOptional = brandRepository.findById(request.getIdBrand());
        Optional<Category> categoryOptional = categoryRepository.findById(request.getIdCategory());
        Optional<Material> materialOptional = materialRepository.findById(request.getIdMaterial());
        Optional<Sole> soleOptional = soleRepository.findById(request.getIdSole());
        Optional<Gender> genderOptional = genderRepository.findById(request.getIdGender());

        if (brandOptional.isEmpty()) {
            errors.add("Not found brand with id: " + request.getIdBrand());
        }
        if (categoryOptional.isEmpty()) {
            errors.add("Not found category with id: " + request.getIdCategory());
        }
        if (materialOptional.isEmpty()) {
            errors.add("Not found material with id: " + request.getIdMaterial());
        }
        if (soleOptional.isEmpty()) {
            errors.add("Not found sole with id: " + request.getIdSole());
        }
        if (genderOptional.isEmpty()) {
            errors.add("Not found gender with id: " + request.getIdGender());
        }

        if (errors.isEmpty()) {
            product.setName(request.getName());
            product.setBrand(brandOptional.get());
            product.setCategory(categoryOptional.get());
            product.setGender(genderOptional.get());
            product.setMaterial(materialOptional.get());
            product.setSole(soleOptional.get());
            product.setDescription(request.getDescription());

            return ResponseObject.builder()
                    .data(productRepository.save(product))
                    .build();
        }

        throw new RestException(errors);
    }

    @Override
    public ResponseObject deletedProduct(String id) {
        Product product = (Product) this.detailProduct(id).getData();
        product.setIsDeleted(!product.getIsDeleted());

        return ResponseObject.builder()
                .data(productRepository.save(product))
                .build();
    }

    @Override
    public ResponseObject detailProduct(String id) {
        Optional<Product> productOptional = productRepository.findById(id);
        if (productOptional.isEmpty()) {
            throw new RestException(List.of("Not found product with id: " + id));
        }

        return ResponseObject.builder()
                .data(productOptional.get())
                .build();
    }

    @Override
    public ResponseObject getSearchProduct(AProductSearchRequest request) {
        Pageable pageable = PageRequest.of(request.getPage() - 1, request.getSize());
        Page<AProductSearchResponse> page = productRepository.getSearchProduct(pageable, request);
        PageResponse<AProductSearchResponse> response = new PageResponse<>(page);

        return ResponseObject.builder()
                .data(response)
                .build();
    }
}
