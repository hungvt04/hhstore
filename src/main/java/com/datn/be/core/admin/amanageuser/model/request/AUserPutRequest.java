package com.datn.be.core.admin.amanageuser.model.request;

import com.datn.be.infrastructure.constant.PropertyEntity;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
public class AUserPutRequest {

    @NotBlank(message = "Name must not be left blank.")
    @Size(min = 1, max = PropertyEntity.LENGTH_NAME, message = "Name character ")
    String name;

    @NotBlank(message = "CCCD must not be left blank.")
    String cccd;

    Long birthDay;

    @NotBlank(message = "Gender must not be left blank.")
    String gender;

    String province;

    String district;

    String ward;

    String addressDetail;

    String role;

    String phone;

    String email;

    String password;

    String imageId;

    String imageUrl;

}
