package com.datn.be.core.admin.amanagediscountperiod.service;

import com.datn.be.core.admin.amanagediscountperiod.model.request.ADiscountPeriodPostRequest;
import com.datn.be.core.admin.amanagediscountperiod.model.request.ADiscountPeriodPutRequest;
import com.datn.be.core.admin.amanagediscountperiod.model.request.ADiscountPeriodSearchRequest;
import com.datn.be.infrastructure.common.ResponseObject;

public interface ADiscountPeriodService {

    ResponseObject postDiscountPeriod(ADiscountPeriodPostRequest request);

    ResponseObject putDiscountPeriod(String id, ADiscountPeriodPutRequest request);

    ResponseObject deleteDiscountPeriod(String id);

    ResponseObject detailDiscountPeriod(String id);

    ResponseObject getSearchDiscountPeriod(ADiscountPeriodSearchRequest request);

}
