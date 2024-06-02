package com.datn.be.core.admin.amanageuser.repository;

import com.datn.be.core.admin.amanageuser.model.request.AUserSearchRequest;
import com.datn.be.core.admin.amanageuser.model.response.AUserSearchResponse;
import com.datn.be.repository.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AUserRepository extends UserRepository {

    boolean existsByCccd(String cccd);

    boolean existsByEmail(String email);

    boolean existsByPhone(String phone);

    boolean existsByName(String name);

    @Query(value = """
            SELECT
            	ROW_NUMBER() OVER(
            	ORDER BY u.created_at DESC) AS 'stt',
            	u.id ,
            	u.cccd ,
            	u.phone ,
            	u.email ,
            	u.`password` ,
            	u.`role` ,
            	u.gender ,
            	u.birth_day AS 'birthDay',
            	u.address_detail AS 'addressDetail',
            	u.ward ,
            	u.district ,
            	u.province ,
            	u.image_id AS 'imageId' ,
            	u.image_url AS 'imageUrl' ,
            	u.created_at AS 'createdAt' ,
            	u.updated_at AS 'updatedAt' ,
            	u.is_deleted AS 'isDeleted'
            FROM
            	`'user'` u
            WHERE (:#{#request.keyword} IS NULL OR :#{#request.keyword} LIKE '' OR u.cccd LIKE '%:#{#request.keyword}%')
            AND (:#{#request.keyword} IS NULL OR :#{#request.keyword} LIKE '' OR u.phone LIKE '%:#{#request.keyword}%')
            AND (:#{#request.keyword} IS NULL OR :#{#request.keyword} LIKE '' OR u.email LIKE '%:#{#request.keyword}%')
            AND (:#{#request.role} IS NULL OR :#{#request.role} LIKE '' OR u.`role` = :#{#request.role})
            AND (:#{#request.isDeleted} IS NULL OR :#{#request.isDeleted} LIKE '' OR u.is_deleted = :#{#request.isDeleted});
            """, countQuery = """
            SELECT
                COUNT(u.id)
            FROM
            	`'user'` u
            WHERE (:#{#request.keyword} IS NULL OR :#{#request.keyword} LIKE '' OR u.cccd LIKE '%:#{#request.keyword}%')
            AND (:#{#request.keyword} IS NULL OR :#{#request.keyword} LIKE '' OR u.phone LIKE '%:#{#request.keyword}%')
            AND (:#{#request.keyword} IS NULL OR :#{#request.keyword} LIKE '' OR u.email LIKE '%:#{#request.keyword}%')
            AND (:#{#request.role} IS NULL OR :#{#request.role} LIKE '' OR u.`role` = :#{#request.role})
            AND (:#{#request.isDeleted} IS NULL OR :#{#request.isDeleted} LIKE '' OR u.is_deleted = :#{#request.isDeleted});
            """, nativeQuery = true)
    Page<AUserSearchResponse> getSearchUser(Pageable pageable, AUserSearchRequest request);

}
