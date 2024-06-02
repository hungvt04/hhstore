package com.datn.be.entity;

import com.datn.be.entity.base.IdEntity;
import com.datn.be.infrastructure.constant.PropertyEntity;
import com.datn.be.infrastructure.constant.enums.EnumStatusDiscount;
import com.datn.be.infrastructure.constant.enums.EnumTypeDiscount;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
@Table(name = "voucher")

/**
 * voucher: ma giam gia
 *      minimumOrder: dieu kien su dung voucher
 *      maxDiscountOrder: gia tri giam toi da cua hoa don
 */
public class Voucher extends IdEntity {

    @Column(length = PropertyEntity.LENGTH_NAME)
    String name;

    @Column(length = PropertyEntity.LENGTH_CODE_VOUCHER)
    String code;

    BigDecimal value;

    Long startDate;

    Long endDate;

    Integer quantity;

    EnumStatusDiscount status;

    EnumTypeDiscount type;

    BigDecimal minimumOrder;

    BigDecimal maxDiscountOrder;

}
