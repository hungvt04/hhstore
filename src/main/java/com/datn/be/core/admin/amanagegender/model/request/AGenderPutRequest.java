package com.datn.be.core.admin.amanagegender.model.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
public class AGenderPutRequest {

    @NotBlank(message = "Code must not be left blank.")
    String code;

    @NotBlank(message = "Name must not be left blank.")
    String name;

}
