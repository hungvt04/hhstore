package com.datn.be.core.admin.amanagediscountperiod.model.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class ADiscountPeriodPostRequest {

    @NotBlank(message = "Name must not be left blank")
    String name;

    BigDecimal value;

    Long startDate;

    Long endDate;

    Integer quantity;

    String type;

    String description;

}