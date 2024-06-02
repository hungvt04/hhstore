package com.datn.be.core.admin.amanagediscountperiod.model.response;

import org.springframework.beans.factory.annotation.Value;

import java.math.BigDecimal;

public interface ADiscountPeriodResponse {

    @Value("#{target.stt}")
    Long getStt();

    @Value("#{target.id}")
    String getId();

    @Value("#{target.value}")
    BigDecimal getValue();

    @Value("#{target.type}")
    String getType();

    @Value("#{target.quantity}")
    Integer getQuantity();

    @Value("#{target.startDate}")
    Long getStartDate();

    @Value("#{target.endDate}")
    Long getEndDate();

    @Value("#{target.description}")
    String getDescription();

    @Value("#{target.createdAt}")
    Long getCreatedAt();

    @Value("#{target.status}")
    String getStatus();

}
