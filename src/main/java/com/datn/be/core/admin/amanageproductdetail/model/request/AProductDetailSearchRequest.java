package com.datn.be.core.admin.amanageproductdetail.model.request;

import com.datn.be.infrastructure.common.PageableRequest;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
public class AProductDetailSearchRequest extends PageableRequest {

    String idProduct;

    String idColor;

    String idSize;

    Boolean isDeleted;

}
