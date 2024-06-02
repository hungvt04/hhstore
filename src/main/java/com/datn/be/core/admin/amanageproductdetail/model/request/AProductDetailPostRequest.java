package com.datn.be.core.admin.amanageproductdetail.model.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
public class AProductDetailPostRequest {

    @NotNull(message = "Price must not be left blank.")
    BigDecimal price;

    @NotNull(message = "Quantity must not be left blank.")
    int quantity;

    @NotBlank
    String idProduct;

    @NotBlank
    String idColor;

    @NotBlank
    String idSize;

    boolean isReturn;

}
