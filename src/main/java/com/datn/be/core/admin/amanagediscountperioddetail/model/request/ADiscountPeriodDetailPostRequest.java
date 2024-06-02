package com.datn.be.core.admin.amanagediscountperioddetail.model.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.util.List;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
public class ADiscountPeriodDetailPostRequest {

    @NotNull(message = "Product detail id must not be left blank.")
    List<String> productDetailId;

    @NotBlank(message = "Discount period id must not be left blank.")
    String discountPeriodId;

}
