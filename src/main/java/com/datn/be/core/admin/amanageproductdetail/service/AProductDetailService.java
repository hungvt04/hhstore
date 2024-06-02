package com.datn.be.core.admin.amanageproductdetail.service;

import com.datn.be.core.admin.amanageproductdetail.model.request.AProductDetailPostRequest;
import com.datn.be.core.admin.amanageproductdetail.model.request.AProductDetailPutRequest;
import com.datn.be.core.admin.amanageproductdetail.model.request.AProductDetailSearchRequest;
import com.datn.be.infrastructure.common.ResponseObject;

public interface AProductDetailService {

    ResponseObject postProductDetail(AProductDetailPostRequest request);

    ResponseObject putProductDetail(String id, AProductDetailPutRequest request);

    ResponseObject deletedProductDetail(String id);

    ResponseObject detailProductDetail(String id);

    ResponseObject getSearchProductDetail(AProductDetailSearchRequest request);

}
