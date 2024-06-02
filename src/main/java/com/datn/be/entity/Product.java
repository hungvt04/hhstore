package com.datn.be.entity;

import com.datn.be.entity.base.PrimaryEntity;
import com.datn.be.infrastructure.constant.PropertyEntity;
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

@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "product")

/**
 * product: san pham
 */
public class Product extends PrimaryEntity {

    @Column(length = PropertyEntity.LENGTH_NAME)
    String name;

    @ManyToOne
    @JoinColumn(name = "brand_id")
    Brand brand;

    @ManyToOne
    @JoinColumn(name = "category_id")
    Category category;

    @ManyToOne
    @JoinColumn(name = "material_id")
    Material material;

    @ManyToOne
    @JoinColumn(name = "sole_id")
    Sole sole;

    @ManyToOne
    @JoinColumn(name = "gender_id")
    Gender gender;

    @Column(length = PropertyEntity.LENGTH_DESCRIPTION)
    String description;

}
