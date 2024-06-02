package com.datn.be.core.admin.amanagecategory.model.request;

import com.datn.be.infrastructure.common.PageableRequest;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ACategorySearchRequest extends PageableRequest {

    String keyword;

    Boolean isDeleted;

}
