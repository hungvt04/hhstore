package com.datn.be.core.admin.amanagediscountperiod.repository;

import com.datn.be.core.admin.amanagediscountperiod.model.request.ADiscountPeriodSearchRequest;
import com.datn.be.core.admin.amanagediscountperiod.model.response.ADiscountPeriodResponse;
import com.datn.be.repository.DiscountPeriodRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ADiscountPeriodRepository extends DiscountPeriodRepository {

    @Query(value = """
            SELECT ROW_NUMBER() OVER(ORDER BY dp.created_at DESC) AS 'stt',
            	dp.id ,
            	dp.name ,
            	dp.value ,
            	dp.`type` ,
            	dp.quantity ,
            	dp.start_date AS 'startDate',
            	dp.end_date AS 'endDate',
            	dp.description ,
            	dp.created_at AS 'createdAt',
            	dp.status
            FROM
            	discount_period dp
            WHERE (:#{#request.keyword} IS NULL OR :#{#request.keyword} LIKE '' OR dp.name LIKE '%:#{#request.keyword}%')
            AND (:#{#request.startDate} IS NULL OR dp.start_date <= :#{#request.startDate})
            AND (:#{#request.endDate} IS NULL OR dp.end_date >= :#{#request.endDate})
            AND (:#{#request.status} IS NULL OR :#{#request.status} = dp.status);
            """, countQuery = """
            SELECT COUNT(dp.id)
            FROM
            	discount_period dp
            WHERE (:#{#request.keyword} IS NULL OR :#{#request.keyword} LIKE '' OR dp.name LIKE '%:#{#request.keyword}%')
            AND (:#{#request.startDate} IS NULL OR dp.start_date <= :#{#request.startDate})
            AND (:#{#request.endDate} IS NULL OR dp.end_date >= :#{#request.endDate})
            AND (:#{#request.status} IS NULL OR :#{#request.status} = dp.status);
            """, nativeQuery = true)
    Page<ADiscountPeriodResponse> getSearchDiscountPeriod(Pageable pageable, ADiscountPeriodSearchRequest request);

}
