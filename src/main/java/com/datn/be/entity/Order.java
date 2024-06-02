package com.datn.be.entity;

import com.datn.be.entity.base.PrimaryEntity;
import com.datn.be.infrastructure.constant.PropertyEntity;
import com.datn.be.infrastructure.constant.enums.EnumStatusDelivery;
import com.datn.be.infrastructure.constant.enums.EnumTypeOrder;
import com.datn.be.infrastructure.constant.enums.EnumTypePayment;
import com.datn.be.infrastructure.constant.enums.EnumTypeTransaction;
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
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "`order`")

/**
 * order: hoa don
 *      estimatedTimeDelivery: uoc tinh thoi gian giao hang
 */
public class Order extends PrimaryEntity {

    @Column(length = PropertyEntity.LENGTH_CODE)
    String code;

    BigDecimal totalPrice;

    @Column(length = PropertyEntity.LENGTH_ID)
    String voucherId;

    BigDecimal voucherValue;

    EnumTypeTransaction typeTransaction;

    EnumTypeOrder typeOrder;

    EnumTypePayment typePayment;

    int totalQuantity;

    @Enumerated(EnumType.STRING)
    EnumStatusDelivery statusDelivery;

    BigDecimal shippingPrice;

    Long point;

    Long estimatedTimeDelivery;

    @Column(length = PropertyEntity.LENGTH_ID_ADDRESS)
    String provinceId;

    @Column(length = PropertyEntity.LENGTH_ID_ADDRESS)
    String districtId;

    @Column(length = PropertyEntity.LENGTH_ID_ADDRESS)
    String wardId;

    @Column(length = PropertyEntity.LENGTH_ADDRESS_DETAIL)
    String addressDetail;

    boolean isReturn;

    @Column(length = PropertyEntity.LENGTH_DESCRIPTION)
    String description;


}
