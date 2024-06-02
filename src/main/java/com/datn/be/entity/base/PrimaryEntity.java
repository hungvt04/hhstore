package com.datn.be.entity.base;

import com.datn.be.infrastructure.constant.PropertyEntity;
import com.datn.be.infrastructure.listeners.PrimaryEntityListener;
import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@MappedSuperclass
@EntityListeners(PrimaryEntityListener.class)
public class PrimaryEntity {

    @Id
    @Column(length = PropertyEntity.LENGTH_ID)
    String id;

    Long createdAt;

    Long updatedAt;

    Boolean isDeleted;

}
