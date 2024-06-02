package com.datn.be.core.admin.amanageuser.model.request;

import com.datn.be.infrastructure.common.PageableRequest;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
public class AUserSearchRequest extends PageableRequest {

    String keyword;

    String role;

    Boolean isDeleted;

}
