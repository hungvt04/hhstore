package com.datn.be.core.admin.amanageuser.model.response;

import org.springframework.beans.factory.annotation.Value;

public interface AUserSearchResponse {

    @Value("#{target.stt}")
    Long getStt();

    @Value("#{target.id}")
    String getId();

    @Value("#{target.cccd}")
    String getCCCD();

    @Value("#{target.phone}")
    String getPhone();

    @Value("#{target.email}")
    String getEmail();

    @Value("#{target.password}")
    String getPassword();

    @Value("#{target.role}")
    String getRole();

    @Value("#{target.gender}")
    String getGender();

    @Value("#{target.birthDay}")
    Long getBirthDay();

    @Value("#{target.addressDetail}")
    String getAddressDetail();

    @Value("#{target.ward}")
    String getWard();

    @Value("#{target.district}")
    String getDistrict();

    @Value("#{target.province}")
    String getProvince();

    @Value("#{target.imageId}")
    String getImageId();

    @Value("#{target.imageUrl}")
    String getImageUrl();

    @Value("#{target.createdAt}")
    Long getCreatedAt();

    @Value("#{target.updatedAt}")
    Long getUpdatedAt();

    @Value("#{target.isDeleted}")
    Boolean getIsDeleted();

}
