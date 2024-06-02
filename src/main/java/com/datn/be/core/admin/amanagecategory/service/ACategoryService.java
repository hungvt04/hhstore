package com.datn.be.core.admin.amanagecategory.service;

import com.datn.be.core.admin.amanagecategory.model.request.ACategoryPostRequest;
import com.datn.be.core.admin.amanagecategory.model.request.ACategoryPutRequest;
import com.datn.be.core.admin.amanagecategory.model.request.ACategorySearchRequest;
import com.datn.be.infrastructure.common.ResponseObject;

public interface ACategoryService {

    ResponseObject postCategory(ACategoryPostRequest request);

    ResponseObject putCategory(String id, ACategoryPutRequest request);

    ResponseObject deleteCategory(String id);

    ResponseObject detailCategory(String id);

    ResponseObject getSearchCategory(ACategorySearchRequest request);

}
