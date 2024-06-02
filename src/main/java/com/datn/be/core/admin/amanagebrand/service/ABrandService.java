package com.datn.be.core.admin.amanagebrand.service;

import com.datn.be.core.admin.amanagebrand.model.request.ABrandPostRequest;
import com.datn.be.core.admin.amanagebrand.model.request.ABrandPutRequest;
import com.datn.be.core.admin.amanagebrand.model.request.ABrandSearchRequest;
import com.datn.be.infrastructure.common.ResponseObject;

public interface ABrandService {

    ResponseObject detailBrand(String id);

    ResponseObject getSearchBrand(ABrandSearchRequest request);

    ResponseObject postBrand(ABrandPostRequest request);

    ResponseObject putBrand(String id, ABrandPutRequest request);

    ResponseObject deleteBrand(String id);

}
