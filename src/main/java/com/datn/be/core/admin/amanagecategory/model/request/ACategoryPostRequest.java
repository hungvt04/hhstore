package com.datn.be.core.admin.amanagecategory.model.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ACategoryPostRequest {

    @NotBlank(message = "Code must not be left blank")
    String code;

    @NotBlank(message = "Name must not be left blank")
    String name;

}
