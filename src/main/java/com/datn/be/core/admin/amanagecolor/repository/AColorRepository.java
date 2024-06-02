package com.datn.be.core.admin.amanagecolor.repository;

import com.datn.be.core.admin.amanagecolor.model.request.AColorSearchRequest;
import com.datn.be.core.admin.amanagecolor.model.response.AColorSearchResponse;
import com.datn.be.repository.ColorRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AColorRepository extends ColorRepository {

    boolean existsByCode(String code);

    boolean existsByName(String name);

    @Query(value = """
            SELECT
            	ROW_NUMBER() OVER(
            	ORDER BY c.created_at DESC) AS 'stt',
            	c.id ,
            	c.code ,
            	c.name ,
            	c.created_at AS 'createdAt',
            	c.updated_at AS 'updatedAt',
            	c.is_deleted AS 'isDeleted'
            FROM
            	color c
            WHERE (:#{#request.keyword} IS NULL OR :#{#request.keyword} LIKE ''
            	OR c.code LIKE '%:#{#request.keyword}%' OR c.id LIKE '%:#{#request.keyword}%')
            AND (:#{#request.isDeleted} IS NULL OR :#{#request.isDeleted} = c.is_deleted)
            """, countQuery = """
            SELECT
            	COUNT(c.id)
            FROM
            	color c
            WHERE (:#{#request.keyword} IS NULL OR :#{#request.keyword} LIKE ''
            	OR c.code LIKE '%:#{#request.keyword}%' OR c.id LIKE '%:#{#request.keyword}%')
            AND (:#{#request.isDeleted} IS NULL OR :#{#request.isDeleted} = c.is_deleted)
            """, nativeQuery = true)
    Page<AColorSearchResponse> getSearchColor(Pageable pageable, AColorSearchRequest request);

}
