package com.datn.be.entity;

import com.datn.be.infrastructure.constant.PropertyEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
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
@Table(name = "ward")

/**
 * ward: xa
 */
public class Ward {

    @Id
    @Column(length = PropertyEntity.LENGTH_ID_ADDRESS)
    String ward_id;

    @Column(length = PropertyEntity.LENGTH_NAME_ADDRESS)
    String ward_name;

    @Column(length = PropertyEntity.LENGTH_TYPE_ADDRESS)
    String ward_type;

    @Column(length = PropertyEntity.LENGTH_ID_ADDRESS)
    String district_id;

}
