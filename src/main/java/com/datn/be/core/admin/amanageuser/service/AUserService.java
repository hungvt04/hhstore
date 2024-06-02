package com.datn.be.core.admin.amanageuser.service;

import com.datn.be.core.admin.amanageuser.model.request.AUserPostRequest;
import com.datn.be.core.admin.amanageuser.model.request.AUserPutRequest;
import com.datn.be.core.admin.amanageuser.model.request.AUserSearchRequest;
import com.datn.be.infrastructure.common.ResponseObject;

public interface AUserService {

    ResponseObject postUser(AUserPostRequest request);

    ResponseObject putUser(String id, AUserPutRequest request);

    ResponseObject deletedUser(String id);

    ResponseObject detailUser(String id);

    ResponseObject getSearchUser(AUserSearchRequest request);

}
