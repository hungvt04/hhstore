package com.datn.be.core.admin.amanageproduct.repository;

import com.datn.be.core.admin.amanageproduct.model.request.AProductSearchRequest;
import com.datn.be.core.admin.amanageproduct.model.response.AProductSearchResponse;
import com.datn.be.repository.ProductRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AProductRepository extends ProductRepository {


    @Query(value = """
            SELECT
            	ROW_NUMBER() OVER(
            	ORDER BY p.created_at DESC) AS 'stt',
            	p.id ,
            	p.name ,
            	c.id AS 'categoryId',
            	c.name AS 'categoryName' ,
            	g.id AS 'genderId' ,
            	g.name AS 'genderName',
            	m.id AS 'materialId' ,
            	m.name AS 'materialName',
            	s.id AS 'soleId',
            	s.name AS 'soleName' ,
            	p.created_at AS 'createdAt',
             	p.updated_at AS 'updatedAt',
             	p.is_deleted AS 'isDeleted'
            FROM
            	product p
            LEFT JOIN brand b ON
            	p.brand_id = b.id
            LEFT JOIN category c ON
            	p.category_id = c.id
            LEFT JOIN gender g ON
            	p.gender_id = g.id
            LEFT JOIN material m ON
            	p.material_id = m.id
            LEFT JOIN sole s ON
            	p.sole_id = s.id
            WHERE (:#{#request.keyword} IS NULL OR :#{#request.keyword} LIKE '' 
                OR p.name LIKE '%:#{#request.keyword}%')
                AND (:#{#request.idBrand} IS NULL OR :#{#request.idBrand} LIKE '' OR b.id = :#{#request.idBrand})
                AND (:#{#request.idCategory} IS NULL OR :#{#request.idCategory} LIKE '' OR c.id = :#{#request.idCategory})
                AND (:#{#request.idGender} IS NULL OR :#{#request.idGender} LIKE '' OR g.id = :#{#request.idGender})
                AND (:#{#request.idMaterial} IS NULL OR :#{#request.idMaterial} LIKE '' OR m.id = :#{#request.idMaterial})
                AND (:#{#request.idSole} IS NULL OR :#{#request.idSole} LIKE '' OR s.id = :#{#request.idMaterial})
                AND (:#{#request.isDeleted} IS NULL OR p.is_deleted = :#{#request.isDeleted});
            """, countQuery = """
            SELECT
                COUNT(p.id)
            FROM
            	product p
            WHERE (:#{#request.keyword} IS NULL OR :#{#request.keyword} LIKE '' 
                OR p.name LIKE '%:#{#request.keyword}%')
                AND (:#{#request.idBrand} IS NULL OR :#{#request.idBrand} LIKE '' OR b.id = :#{#request.idBrand})
                AND (:#{#request.idCategory} IS NULL OR :#{#request.idCategory} LIKE '' OR c.id = :#{#request.idCategory})
                AND (:#{#request.idGender} IS NULL OR :#{#request.idGender} LIKE '' OR g.id = :#{#request.idGender})
                AND (:#{#request.idMaterial} IS NULL OR :#{#request.idMaterial} LIKE '' OR m.id = :#{#request.idMaterial})
                AND (:#{#request.idSole} IS NULL OR :#{#request.idSole} LIKE '' OR s.id = :#{#request.idMaterial})
                AND (:#{#request.isDeleted} IS NULL OR p.is_deleted = :#{#request.isDeleted});
            """, nativeQuery = true)
    Page<AProductSearchResponse> getSearchProduct(Pageable pageable, AProductSearchRequest request);

}
