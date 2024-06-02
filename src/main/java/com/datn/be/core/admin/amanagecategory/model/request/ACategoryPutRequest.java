package com.datn.be.core.admin.amanagecategory.model.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ACategoryPutRequest {

    @NotBlank(message = "Code must not be left blank")
    String code;

    @NotBlank(message = "Name must not be left blank")
    String name;

}
