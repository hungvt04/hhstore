package com.datn.be.core.admin.amanagesize.model.request;

import com.datn.be.infrastructure.common.PageableRequest;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
public class ASizeSearchRequest extends PageableRequest {

    String keyword;

    Boolean isDeleted;

}
