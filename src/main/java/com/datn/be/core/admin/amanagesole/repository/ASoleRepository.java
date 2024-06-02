package com.datn.be.core.admin.amanagesole.repository;

import com.datn.be.core.admin.amanagesole.model.request.ASoleSearchRequest;
import com.datn.be.core.admin.amanagesole.model.response.ASoleSearchResponse;
import com.datn.be.repository.SoleRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ASoleRepository extends SoleRepository {

    boolean existsByCode(String code);

    boolean existsByName(String name);

    @Query(value = """
            SELECT
            	ROW_NUMBER() OVER(
            	ORDER BY s.created_at DESC) AS 'stt',
            	s.id ,
            	s.code ,
            	s.name ,
            	s.created_at AS 'createdAt',
            	s.updated_at AS 'updatedAt',
            	s.is_deleted AS 'isDeleted'
            FROM
            	sole s
            WHERE (:#{#request.keyword} IS NULL OR :#{#request.keyword} LIKE '' OR 
                s.name LIKE '%:#{#request.keyword}%' OR s.code LIKE '%:#{#request.keyword}%')
                AND (:#{#request.isDeleted} IS NULL OR :#{#request.isDeleted} = s.is_deleted);
            """, countQuery = """
            SELECT
                COUNT(s.id)
            FROM
            	sole s
            WHERE (:#{#request.keyword} IS NULL OR :#{#request.keyword} LIKE '' OR 
                s.name LIKE '%:#{#request.keyword}%' OR s.code LIKE '%:#{#request.keyword}%')
                AND (:#{#request.isDeleted} IS NULL OR :#{#request.isDeleted} = s.is_deleted);
            """, nativeQuery = true)
    Page<ASoleSearchResponse> getSearchSole(Pageable pageable, ASoleSearchRequest request);

}
