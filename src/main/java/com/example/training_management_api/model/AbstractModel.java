package com.example.training_management_api.model;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.Where;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.sql.Timestamp;

@EntityListeners(AuditingEntityListener.class)
@Data
@Where(clause = "deletedAt IS NULL && deletedBy IS NULL")
@MappedSuperclass
public abstract class AbstractModel {
    @ManyToOne
    @JoinColumn(name = "created_by")
    @CreatedBy
    private User createdBy;

    @CreatedDate
    @Column(name = "created_at")
    private Timestamp createdAt;

    @ManyToOne
    @JoinColumn(name = "updated_by")
    @LastModifiedBy
    private User updatedBy;

    @Column(name = "updated_at")
    @LastModifiedDate
    private Timestamp updatedAt;

    @ManyToOne
    @JoinColumn(name = "deleted_by")
    @LastModifiedBy
    private User deletedBy;

    @Column(name = "deleted_at")
    @LastModifiedDate
    private Timestamp deletedAt;
}