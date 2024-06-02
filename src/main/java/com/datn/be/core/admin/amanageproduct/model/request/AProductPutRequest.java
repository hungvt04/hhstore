package com.datn.be.core.admin.amanageproduct.model.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
public class AProductPutRequest {

    @NotBlank(message = "Name must not be left blank.")
    String name;

    @NotBlank(message = "ID Brand must not be left blank.")
    String idBrand;

    @NotBlank(message = "ID Category must not be left blank.")
    String idCategory;

    @NotBlank(message = "ID Material must not be left blank.")
    String idMaterial;

    @NotBlank(message = "ID Sole must not be left blank.")
    String idSole;

    @NotBlank(message = "ID Gender must not be left blank.")
    String idGender;

    String description;

}
