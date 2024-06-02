package com.datn.be.core.admin.amanagesize.service;

import com.datn.be.core.admin.amanagesize.model.request.ASizePostRequest;
import com.datn.be.core.admin.amanagesize.model.request.ASizePutRequest;
import com.datn.be.core.admin.amanagesize.model.request.ASizeSearchRequest;
import com.datn.be.infrastructure.common.ResponseObject;

public interface ASizeService {

    ResponseObject postSize(ASizePostRequest request);

    ResponseObject putSize(String id, ASizePutRequest request);

    ResponseObject deletedSize(String id);

    ResponseObject detailSize(String id);

    ResponseObject getSearchSize(ASizeSearchRequest request);

}
