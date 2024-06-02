package com.datn.be.core.admin.amanagesole.service;

import com.datn.be.core.admin.amanagesole.model.request.ASolePostRequest;
import com.datn.be.core.admin.amanagesole.model.request.ASolePutRequest;
import com.datn.be.core.admin.amanagesole.model.request.ASoleSearchRequest;
import com.datn.be.infrastructure.common.ResponseObject;

public interface ASoleService {

    ResponseObject postSole(ASolePostRequest request);

    ResponseObject putSole(String id, ASolePutRequest request);

    ResponseObject deletedSole(String id);

    ResponseObject detailSole(String id);

    ResponseObject getSearchSole(ASoleSearchRequest request);

}
