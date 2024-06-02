package com.datn.be.core.admin.amanagegender.service;

import com.datn.be.core.admin.amanagegender.model.request.AGenderPostRequest;
import com.datn.be.core.admin.amanagegender.model.request.AGenderPutRequest;
import com.datn.be.core.admin.amanagegender.model.request.AGenderSearchRequest;
import com.datn.be.infrastructure.common.ResponseObject;

public interface AGenderService {

    ResponseObject postGender(AGenderPostRequest request);

    ResponseObject putGender(String id, AGenderPutRequest request);

    ResponseObject deletedGender(String id);

    ResponseObject detailGender(String id);

    ResponseObject getSearchGender(AGenderSearchRequest request);

}
