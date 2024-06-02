package com.datn.be.core.admin.amanagecolor.service;

import com.datn.be.core.admin.amanagecolor.model.request.AColorPostRequest;
import com.datn.be.core.admin.amanagecolor.model.request.AColorPutRequest;
import com.datn.be.core.admin.amanagecolor.model.request.AColorSearchRequest;
import com.datn.be.infrastructure.common.ResponseObject;

public interface AColorService {

    ResponseObject postColor(AColorPostRequest request);

    ResponseObject putColor(String id, AColorPutRequest request);

    ResponseObject deleteColor(String id);

    ResponseObject detailColor(String id);

    ResponseObject getSearchColor(AColorSearchRequest request);

}
