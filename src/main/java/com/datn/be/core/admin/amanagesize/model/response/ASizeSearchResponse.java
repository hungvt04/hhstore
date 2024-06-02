package com.datn.be.core.admin.amanagesize.model.response;

import org.springframework.beans.factory.annotation.Value;

public interface ASizeSearchResponse {

    @Value("#{target.stt}")
    Long getStt();

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
