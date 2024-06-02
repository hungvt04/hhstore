package com.datn.be.infrastructure.listeners;

import com.datn.be.entity.base.IdEntity;
import jakarta.persistence.PrePersist;

import java.util.Date;
import java.util.UUID;

public class IdEntityListener {

    @PrePersist
    public void onCreate(IdEntity entity) {
        entity.setId(this.generateId());
        entity.setCreatedAt(this.getCurrentTime());
    }

    public String generateId() {
        return UUID.randomUUID().toString();
    }

    public Long getCurrentTime() {
        return new Date().getTime();
    }
}
