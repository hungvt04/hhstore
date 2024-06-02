package com.datn.be.core.admin.amanagecategory.repository;

import com.datn.be.core.admin.amanagecategory.model.request.ACategorySearchRequest;
import com.datn.be.core.admin.amanagecategory.model.response.ACategorySearchResponse;
import com.datn.be.repository.CategoryRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ACategoryRepository extends CategoryRepository {

    boolean existsByCode(String code);

    boolean existsByName(String name);

    @Query(value = """
            SELECT
            	ROW_NUMBER() OVER(
            	ORDER BY c.created_at DESC) AS 'stt',
            	c.id ,
            	c.code ,
            	c.name ,
            	c.created_at AS	'createdAt',
            	c.updated_at AS	'updatedAt',
            	c.is_deleted AS	'isDeleted'
            FROM
            	category c
            WHERE (:#{#request.keyword} IS NULL OR :#{#request.keyword} LIKE '' OR 
                c.code LIKE '%:#{#request.keyword}%' OR c.name LIKE '%:#{#request.keyword}%') AND 
                (:#{#request.isDeleted} IS NULL OR c.is_deleted = :#{#request.isDeleted})
            """, countQuery = """
            SELECT
                COUNT(c.id)
            FROM
            	category c
            WHERE (:#{#request.keyword} IS NULL OR :#{#request.keyword} LIKE '' OR 
                c.code LIKE '%:#{#request.keyword}%' OR c.name LIKE '%:#{#request.keyword}%') AND 
                (:#{#request.isDeleted} IS NULL OR c.is_deleted = :#{#request.isDeleted})
            """, nativeQuery = true)
    Page<ACategorySearchResponse> getSearchCategory(Pageable pageable, ACategorySearchRequest request);

}
