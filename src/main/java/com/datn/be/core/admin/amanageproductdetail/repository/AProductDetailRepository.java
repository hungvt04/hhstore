package com.datn.be.core.admin.amanageproductdetail.repository;

import com.datn.be.core.admin.amanageproductdetail.model.request.AProductDetailSearchRequest;
import com.datn.be.core.admin.amanageproductdetail.model.response.AProductDetailSearchResponse;
import com.datn.be.entity.Color;
import com.datn.be.entity.Product;
import com.datn.be.entity.ProductDetail;
import com.datn.be.entity.Size;
import com.datn.be.repository.ProductDetailRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AProductDetailRepository extends ProductDetailRepository {

    boolean existsByColorAndSizeAndProduct(Color color, Size size, Product product);

    Optional<ProductDetail> findByColorAndSizeAndProduct(Color color, Size size, Product product);


    @Query(value = """
            SELECT
            	ROW_NUMBER() OVER(
            	ORDER BY pd.created_at DESC) AS 'stt',
            	pd.id ,
            	pd.price ,
            	pd.quantity ,
            	pd.qrcode_id AS 'qrcodeId',
            	pd.qrcode_url AS 'qrcodeUrl',
            	p.id AS 'productId',
            	p.name AS 'productName',
            	c.id AS 'colorId',
            	c.name AS 'colorName',
            	s.id AS 'sizeId',
            	s.name AS 'sizeName',
            	pd.is_return AS 'isReturn',
            	pd.created_at AS 'createdAt',
            	pd.updated_at AS 'updatedAt',
            	pd.is_deleted AS 'isDeleted'
            FROM
            	product_detail pd
            LEFT JOIN product p ON
            	pd.product_id = p.id
            LEFT JOIN color c ON
            	pd.color_id = c.id
            LEFT JOIN `size` s ON
            	pd.size_id = s.id
            WHERE (:#{#request.idProduct} IS NULL OR :#{#request.idProduct} LIKE '' OR pd.product_id = :#{#request.idProduct})
            	AND (:#{#request.idColor} IS NULL OR :#{#request.idColor} LIKE '' OR pd.color_id = :#{#request.idColor})
            	AND (:#{#request.idSize} IS NULL OR :#{#request.idSize} LIKE '' OR pd.size_id = :#{#request.idSize})
            	AND (:#{#request.isDeleted} IS NULL OR pd.is_deleted = :#{#request.isDeleted})
            """, countQuery = """
            SELECT
                COUNT(pd.id)
            FROM
            	product_detail pd
            LEFT JOIN product p ON
            	pd.product_id = p.id
            LEFT JOIN color c ON
            	pd.color_id = c.id
            LEFT JOIN `size` s ON
            	pd.size_id = s.id
            WHERE (:#{#request.idProduct} IS NULL OR :#{#request.idProduct} LIKE '' OR pd.product_id = :#{#request.idProduct})
            	AND (:#{#request.idColor} IS NULL OR :#{#request.idColor} LIKE '' OR pd.color_id = :#{#request.idColor})
            	AND (:#{#request.idSize} IS NULL OR :#{#request.idSize} LIKE '' OR pd.size_id = :#{#request.idSize})
            	AND (:#{#request.isDeleted} IS NULL OR pd.is_deleted = :#{#request.isDeleted})
            """, nativeQuery = true)
    Page<AProductDetailSearchResponse> getSearchProductDetail(Pageable pageable, AProductDetailSearchRequest request);
}
