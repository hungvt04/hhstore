package com.datn.be.core.admin.amanagegender.repository;

import com.datn.be.core.admin.amanagegender.model.request.AGenderSearchRequest;
import com.datn.be.core.admin.amanagegender.model.response.AGenderSearchResponse;
import com.datn.be.repository.GenderRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AGenderRepository extends GenderRepository {

    boolean existsByCode(String code);

    boolean existsByName(String name);

    @Query(value = """
            SELECT
            	ROW_NUMBER() OVER(
            	ORDER BY g.created_at DESC) AS 'stt',
            	g.id ,
            	g.code ,
            	g.name ,
            	g.created_at AS 'createdAt',
            	g.updated_at AS 'updatedAt',
            	g.is_deleted AS 'isDeleted'
            FROM
            	gender g
            WHERE (:#{#request.keyword} IS NULL OR :#{#request.keyword} LIKE '' OR 
                g.name LIKE '%:#{#request.keyword}%' OR g.code LIKE '%:#{#request.keyword}%')
                AND (:#{#request.isDeleted} IS NULL OR :#{#request.isDeleted} = g.is_deleted);
            """, countQuery = """
            SELECT
                COUNT(g.id)
            FROM
            	gender g
            WHERE (:#{#request.keyword} IS NULL OR :#{#request.keyword} LIKE '' OR 
                g.name LIKE '%:#{#request.keyword}%' OR g.code LIKE '%:#{#request.keyword}%')
                AND (:#{#request.isDeleted} IS NULL OR :#{#request.isDeleted} = g.is_deleted);
            """, nativeQuery = true)
    Page<AGenderSearchResponse> getSearchGender(Pageable pageable, AGenderSearchRequest request);

}
