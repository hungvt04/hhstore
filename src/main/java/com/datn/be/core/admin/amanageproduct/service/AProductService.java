package com.datn.be.core.admin.amanageproduct.service;

import com.datn.be.core.admin.amanageproduct.model.request.AProductPostRequest;
import com.datn.be.core.admin.amanageproduct.model.request.AProductPutRequest;
import com.datn.be.core.admin.amanageproduct.model.request.AProductSearchRequest;
import com.datn.be.infrastructure.common.ResponseObject;

public interface AProductService {

    ResponseObject postProduct(AProductPostRequest request);

    ResponseObject putProduct(String id, AProductPutRequest request);

    ResponseObject deletedProduct(String id);

    ResponseObject detailProduct(String id);

    ResponseObject getSearchProduct(AProductSearchRequest request);

}
