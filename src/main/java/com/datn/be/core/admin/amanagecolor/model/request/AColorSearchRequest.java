package com.datn.be.core.admin.amanagecolor.model.request;

import com.datn.be.infrastructure.common.PageableRequest;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
public class AColorSearchRequest extends PageableRequest {

    String keyword;

    Boolean isDeleted;

}
