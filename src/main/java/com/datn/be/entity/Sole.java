package com.datn.be.entity;

import com.datn.be.entity.base.PrimaryEntity;
import com.datn.be.infrastructure.constant.PropertyEntity;
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

@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "sole")

/**
 * sole: de giay
 */
public class Sole extends PrimaryEntity {

    @Column(length = PropertyEntity.LENGTH_CODE)
    String code;

    @Column(length = PropertyEntity.LENGTH_NAME)
    String name;

}
