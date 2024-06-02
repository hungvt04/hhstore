package com.datn.be.core.admin.amanageproductdetail.service.impl;

import com.datn.be.core.admin.amanageproductdetail.model.request.AProductDetailPostRequest;
import com.datn.be.core.admin.amanageproductdetail.model.request.AProductDetailPutRequest;
import com.datn.be.core.admin.amanageproductdetail.model.request.AProductDetailSearchRequest;
import com.datn.be.core.admin.amanageproductdetail.model.response.AProductDetailSearchResponse;
import com.datn.be.core.admin.amanageproductdetail.repository.AProductDetailRepository;
import com.datn.be.core.admin.amanageproductdetail.service.AProductDetailService;
import com.datn.be.entity.Color;
import com.datn.be.entity.Product;
import com.datn.be.entity.ProductDetail;
import com.datn.be.entity.Size;
import com.datn.be.infrastructure.common.PageResponse;
import com.datn.be.infrastructure.common.ResponseObject;
import com.datn.be.infrastructure.exception.RestException;
import com.datn.be.repository.ColorRepository;
import com.datn.be.repository.ProductRepository;
import com.datn.be.repository.SizeRepository;
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
public class AProductDetailServiceImpl implements AProductDetailService {

    private final ColorRepository colorRepository;

    private final SizeRepository sizeRepository;

    private final ProductRepository productRepository;

    private final AProductDetailRepository productDetailRepository;

    @Override
    public ResponseObject postProductDetail(AProductDetailPostRequest request) {
        List<String> errors = new ArrayList<>();
        Optional<Color> colorOptional = colorRepository.findById(request.getIdColor());
        Optional<Size> sizeOptional = sizeRepository.findById(request.getIdSize());
        Optional<Product> productOptional = productRepository.findById(request.getIdProduct());

        if (colorOptional.isEmpty()) {
            errors.add("Not found color with id: " + request.getIdColor());
        }
        if (sizeOptional.isEmpty()) {
            errors.add("Not found size with id: " + request.getIdSize());
        }
        if (productOptional.isEmpty()) {
            errors.add("Not found product with id: " + request.getIdProduct());
        }

        if (!errors.isEmpty()) {
            throw new RestException(errors);
        }

        Size size = sizeOptional.get();
        Color color = colorOptional.get();
        Product product = productOptional.get();

        // check duplicate
        Optional<ProductDetail> productDetailOptional = productDetailRepository.findByColorAndSizeAndProduct(color, size, product);
        ProductDetail productDetail = null;
        if (productDetailOptional.isEmpty()) {
            // Them moi
            productDetail = new ProductDetail();
            productDetail.setQuantity(request.getQuantity());
        } else {
            // update
            productDetail = productDetailOptional.get();
            productDetail.setQuantity(productDetail.getQuantity() + request.getQuantity());
        }
        productDetail.setPrice(request.getPrice());
        productDetail.setIsReturn(false);
        // Chua xu ly qr code
        productDetail.setProduct(product);
        productDetail.setColor(color);
        productDetail.setSize(size);

        return ResponseObject.builder()
                .data(productDetailRepository.save(productDetail))
                .build();
    }

    @Override
    public ResponseObject putProductDetail(String id, AProductDetailPutRequest request) {
        ProductDetail productDetail = (ProductDetail) this.detailProductDetail(id).getData();
        List<String> errors = new ArrayList<>();
        Optional<Color> colorOptional = colorRepository.findById(request.getIdColor());
        Optional<Size> sizeOptional = sizeRepository.findById(request.getIdSize());
        Optional<Product> productOptional = productRepository.findById(request.getIdProduct());

        if (colorOptional.isEmpty()) {
            errors.add("Not found color with id: " + request.getIdColor());
        }
        if (sizeOptional.isEmpty()) {
            errors.add("Not found size with id: " + request.getIdSize());
        }
        if (productOptional.isEmpty()) {
            errors.add("Not found product with id: " + request.getIdProduct());
        }

        if (!errors.isEmpty()) {
            throw new RestException(errors);
        }

        Size size = sizeOptional.get();
        Color color = colorOptional.get();
        Product product = productOptional.get();

        productDetail.setQuantity(request.getQuantity());
        productDetail.setPrice(request.getPrice());
        productDetail.setIsReturn(request.getIsReturn() != null ? request.getIsReturn() : productDetail.getIsReturn());
        // Chua xu ly qr code
        productDetail.setProduct(product);
        productDetail.setColor(color);
        productDetail.setSize(size);
        return ResponseObject.builder()
                .data(productDetailRepository.save(productDetail))
                .build();
    }

    @Override
    public ResponseObject deletedProductDetail(String id) {
        ProductDetail productDetail = (ProductDetail) this.detailProductDetail(id).getData();
        productDetail.setIsDeleted(!productDetail.getIsDeleted());

        return ResponseObject.builder()
                .data(productDetailRepository.save(productDetail))
                .build();
    }

    @Override
    public ResponseObject detailProductDetail(String id) {
        Optional<ProductDetail> productDetailOptional = productDetailRepository.findById(id);
        if (productDetailOptional.isEmpty()) {
            throw new RestException(List.of("Not found productDetail with id: " + id));
        }

        return ResponseObject.builder()
                .data(productDetailOptional.get())
                .build();
    }

    @Override
    public ResponseObject getSearchProductDetail(AProductDetailSearchRequest request) {
        Pageable pageable = PageRequest.of(request.getPage() - 1, request.getSize());
        Page<AProductDetailSearchResponse> page = productDetailRepository.getSearchProductDetail(pageable, request);
        PageResponse<AProductDetailSearchResponse> response = new PageResponse<>(page);

        return ResponseObject.builder()
                .data(response)
                .build();
    }
}
