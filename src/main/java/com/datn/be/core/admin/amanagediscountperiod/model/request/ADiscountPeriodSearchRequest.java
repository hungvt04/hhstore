package com.datn.be.core.admin.amanagediscountperiod.model.request;

import com.datn.be.infrastructure.common.PageableRequest;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
public class ADiscountPeriodSearchRequest extends PageableRequest {

    String keyword;

    Long startDate;

    Long endDate;

    String status;

}
