package com.datn.be.core.admin.amanagematerial.repository;

import com.datn.be.core.admin.amanagematerial.model.request.AMaterialSearchRequest;
import com.datn.be.core.admin.amanagematerial.model.response.AMaterialSearchResponse;
import com.datn.be.repository.MaterialRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AMaterialRepository extends MaterialRepository {

    boolean existsByCode(String code);

    boolean existsByName(String name);

    @Query(value = """
            SELECT
            	ROW_NUMBER() OVER(
            	ORDER BY m.created_at DESC) AS 'stt',
            	m.id ,
            	m.code ,
            	m.name ,
            	m.created_at AS 'createdAt',
            	m.updated_at AS 'updatedAt',
            	m.is_deleted AS 'isDeleted'
            FROM
            	material m
            WHERE (:#{#request.keyword} IS NULL OR :#{#request.keyword} LIKE '' OR 
                m.name LIKE '%:#{#request.keyword}%' OR m.code LIKE '%:#{#request.keyword}%')
                AND (:#{#request.isDeleted} IS NULL OR :#{#request.isDeleted} = m.is_deleted);
            """, countQuery = """
            SELECT
                COUNT(m.id)
            FROM
            	material m
            WHERE (:#{#request.keyword} IS NULL OR :#{#request.keyword} LIKE '' OR 
                m.name LIKE '%:#{#request.keyword}%' OR m.code LIKE '%:#{#request.keyword}%')
                AND (:#{#request.isDeleted} IS NULL OR :#{#request.isDeleted} = m.is_deleted);
            """, nativeQuery = true)
    Page<AMaterialSearchResponse> getSearchMaterial(Pageable pageable, AMaterialSearchRequest request);

}
