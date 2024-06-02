package com.datn.be.entity;

import com.datn.be.entity.base.IdEntity;
import com.datn.be.infrastructure.constant.PropertyEntity;
import com.datn.be.infrastructure.constant.enums.EnumStatusDiscount;
import com.datn.be.infrastructure.constant.enums.EnumTypeDiscount;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "discount_period_detail")

/**
 * discount_period_detail: chi tiet dot giam gia
 */
public class DiscountPeriodDetail extends IdEntity {

    @ManyToOne
    @JoinColumn(name = "product_detail_id")
    ProductDetail productDetail;

    @ManyToOne
    @JoinColumn(name = "discount_period_id")
    DiscountPeriod discountPeriod;

}
