package com.datn.be.core.admin.amanageproductdetail.model.response;

import org.springframework.beans.factory.annotation.Value;

import java.math.BigDecimal;

public interface AProductDetailSearchResponse {

    @Value("#{target.stt}")
    Long getStt();

    @Value("#{target.id}")
    String getId();

    @Value("#{target.price}")
    BigDecimal getPrice();

    @Value("#{target.quantity}")
    Integer getQuantity();

    @Value("#{target.qrcodeId}")
    String getQrcodeId();

    @Value("#{target.qrcodeUrl}")
    String getQrcodeUrl();

    @Value("#{target.productId}")
    String getProductId();

    @Value("#{target.productName}")
    String getProductName();

    @Value("#{target.colorId}")
    String getColorId();

    @Value("#{target.colorName}")
    String getColorName();

    @Value("#{target.sizeId}")
    String getSizeId();

    @Value("#{target.sizeName}")
    String getSizeName();

    @Value("#{target.isReturn}")
    Boolean getIsReturn();

    @Value("#{target.createdAt}")
    Long getCreatedAt();

    @Value("#{target.updatedAt}")
    Long getUpdatedAt();

    @Value("#{target.isDeleted}")
    Boolean getIsDeleted();

}
