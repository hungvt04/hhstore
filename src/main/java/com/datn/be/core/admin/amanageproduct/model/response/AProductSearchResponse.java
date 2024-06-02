package com.datn.be.core.admin.amanageproduct.model.response;

import org.springframework.beans.factory.annotation.Value;

public interface AProductSearchResponse {

    @Value("#{target.stt}")
    Long getStt();

    @Value("#{target.id}")
    String getId();

    @Value("#{target.name}")
    String getName();

    @Value("#{target.categoryId}")
    String getCategoryId();

    @Value("#{target.categoryName}")
    String getCategoryName();

    @Value("#{target.genderId}")
    String getGenderId();

    @Value("#{target.genderName}")
    String getGenderName();

    @Value("#{target.materialId}")
    String getMaterialId();

    @Value("#{target.materialName}")
    String getMaterialName();

    @Value("#{target.soleId}")
    String getSoleId();

    @Value("#{target.soleName}")
    String getSoleName();

    @Value("#{target.createdAt}")
    Long getCreatedAt();

    @Value("#{target.updatedAt}")
    Long getUpdatedAt();

    @Value("#{target.isDeleted}")
    Boolean getIsDeleted();

}
