package com.datn.be.core.admin.amanagebrand.repository;

import com.datn.be.core.admin.amanagebrand.model.request.ABrandSearchRequest;
import com.datn.be.core.admin.amanagebrand.model.response.ABrandSearchResponse;
import com.datn.be.repository.BrandRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;

public interface ABrandRepository extends BrandRepository {

    boolean existsByCode(String code);

    boolean existsByName(String name);

    @Query(value = """
            SELECT
            	ROW_NUMBER() OVER(
            	ORDER BY b.created_at DESC ) AS 'stt',
            	b.id ,
            	b.code ,
            	b.name ,
            	b.created_at AS 'createdAt',
            	b.updated_at AS 'updatedAt' ,
            	b.is_deleted AS 'isDeleted'
            FROM
            	brand b
            WHERE (:#{#request.keyword} IS NULL OR :#{#request.keyword} LIKE '' OR 
            	b.code LIKE %:#{#request.keyword}% ) AND 
            	(:#{#request.isDeleted} IS NULL OR :#{#request.isDeleted} = b.is_deleted)
            """, countQuery = """
            SELECT
                 COUNT(b.id)
            FROM
                brand b
            WHERE (:#{#request.keyword} IS NULL OR :#{#request.keyword} LIKE '' OR 
                b.code LIKE %:#{#request.keyword}% ) AND 
                (:#{#request.isDeleted} IS NULL OR :#{#request.isDeleted} = b.is_deleted)
                        """
            , nativeQuery = true)
    Page<ABrandSearchResponse> getSearchBrand(Pageable pageable, ABrandSearchRequest request);

}
