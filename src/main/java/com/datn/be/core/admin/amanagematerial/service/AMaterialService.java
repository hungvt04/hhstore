package com.datn.be.core.admin.amanagematerial.service;

import com.datn.be.core.admin.amanagematerial.model.request.AMaterialPostRequest;
import com.datn.be.core.admin.amanagematerial.model.request.AMaterialPutRequest;
import com.datn.be.core.admin.amanagematerial.model.request.AMaterialSearchRequest;
import com.datn.be.infrastructure.common.ResponseObject;

public interface AMaterialService {

    ResponseObject postMaterial(AMaterialPostRequest request);

    ResponseObject putMaterial(String id, AMaterialPutRequest request);

    ResponseObject deletedMaterial(String id);

    ResponseObject detailMaterial(String id);

    ResponseObject getSearchMaterial(AMaterialSearchRequest request);

}
