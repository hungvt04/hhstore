package com.datn.be.core.admin.amanageproduct.model.request;

import com.datn.be.infrastructure.common.PageableRequest;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
public class AProductSearchRequest extends PageableRequest {

    String keyword;

    String idBrand;

    String idCategory;

    String idMaterial;

    String idSole;

    String idGender;

    Boolean isDeleted;

}
