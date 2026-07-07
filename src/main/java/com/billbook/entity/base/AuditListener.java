package com.billbook.entity.base;

import java.time.LocalDateTime;

import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;

public class AuditListener {

    @PrePersist
    public void beforeInsert(BaseEntity entity) {
        LocalDateTime now = LocalDateTime.now();
        entity.setCreatedAt(now);
        entity.setUpdatedAt(now);
    }

    @PreUpdate
    public void beforeUpdate(BaseEntity entity) {
        entity.setUpdatedAt(LocalDateTime.now());
    }
}