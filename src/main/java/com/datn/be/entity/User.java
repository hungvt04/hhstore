package com.datn.be.entity;

import com.datn.be.entity.base.PrimaryEntity;
import com.datn.be.infrastructure.constant.PropertyEntity;
import com.datn.be.infrastructure.constant.enums.EnumTypeRole;
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

@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "'user'")

/**
 * user: Nhan vien, admin
 */
public class User extends PrimaryEntity {

    @Column(length = PropertyEntity.LENGTH_NAME)
    String name;

    @Column(length = PropertyEntity.LENGTH_CCCD)
    String cccd;

    Long birthDay;

    @Column(length = PropertyEntity.LENGTH_GENDER)
    String gender;

    @Column(length = 50)
    String province;

    @Column(length = 50)
    String district;

    @Column(length = 50)
    String ward;

    @Column(length = PropertyEntity.LENGTH_ADDRESS_DETAIL)
    String addressDetail;

    @Enumerated(EnumType.STRING)
    EnumTypeRole role;

    @Column(length = PropertyEntity.LENGTH_PHONE)
    String phone;

    @Column(length = PropertyEntity.LENGTH_EMAIL)
    String email;

    @Column(length = PropertyEntity.LENGTH_PASSWORD)
    String password;

    @Column(length = PropertyEntity.LENGTH_IMAGE_ID)
    String imageId;

    @Column(length = PropertyEntity.LENGTH_IMAGE_URL)
    String imageUrl;

}
