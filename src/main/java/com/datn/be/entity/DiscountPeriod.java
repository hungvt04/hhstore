package com.datn.be.entity;

import com.datn.be.entity.base.IdEntity;
import com.datn.be.infrastructure.constant.PropertyEntity;
import com.datn.be.infrastructure.constant.enums.EnumStatusDiscount;
import com.datn.be.infrastructure.constant.enums.EnumTypeDiscount;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "discount_period")
@ToString

/**
 * discount_period: dot giam gia
 *      minimumOrder: dieu kien su dung discount_period
 *      maxDiscountOrder: gia tri giam toi da cua hoa don
 */
public class DiscountPeriod extends IdEntity {

    @Column(length = PropertyEntity.LENGTH_NAME)
    String name;

    BigDecimal value;

    Long startDate;

    Long endDate;

    Integer quantity;

    @Enumerated(EnumType.STRING)
    EnumStatusDiscount status;

    @Enumerated(EnumType.STRING)
    EnumTypeDiscount type;

    @Column(length = PropertyEntity.LENGTH_DESCRIPTION)
    String description;

}
