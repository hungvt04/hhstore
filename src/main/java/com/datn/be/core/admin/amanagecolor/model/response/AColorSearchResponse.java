package com.datn.be.core.admin.amanagecolor.model.response;

import org.springframework.beans.factory.annotation.Value;

public interface AColorSearchResponse {

    @Value("#{target.stt}")
    String getStt();

    @Value("#{target.id}")
    String getId();

    @Value("#{target.code}")
    String getCode();

    @Value("#{target.name}")
    String getName();

    @Value("#{target.createdAt}")
    Long getCreatedAt();

    @Value("#{target.updatedAt}")
    Long getUpdatedAt();

    @Value("#{target.isDeleted}")
    Boolean getIsDeleted();

}
