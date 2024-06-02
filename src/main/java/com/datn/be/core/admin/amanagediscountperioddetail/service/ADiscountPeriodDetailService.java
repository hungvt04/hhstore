package com.datn.be.core.admin.amanagediscountperioddetail.service;

import com.datn.be.core.admin.amanagediscountperioddetail.model.request.ADiscountPeriodDetailPostRequest;
import com.datn.be.core.admin.amanagediscountperioddetail.model.request.ADiscountPeriodDetailPutRequest;
import com.datn.be.core.admin.amanagediscountperioddetail.model.request.ADiscountPeriodDetailSearchRequest;
import com.datn.be.infrastructure.common.ResponseObject;

public interface ADiscountPeriodDetailService {

    ResponseObject postDiscountPeriodDetail(ADiscountPeriodDetailPostRequest request);

    ResponseObject putDiscountPeriodDetail(String id, ADiscountPeriodDetailPutRequest request);

    ResponseObject deletedDiscountPeriodDetail(String id);

    ResponseObject detailDiscountPeriodDetail(String id);

    ResponseObject getSearchDiscountPeriodDetail(ADiscountPeriodDetailSearchRequest request);

}
