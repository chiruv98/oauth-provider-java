package com.oauthprovider.entities;

import jakarta.persistence.GeneratedValue;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Data;

@Entity
@Table (name = "user", catalog = "oauthdb")
@Data
public class UserEntity {
    
    @Id
    @Column (name = "id", nullable = false, updatable = false)
    @GeneratedValue (strategy = GenerationType.UUID)
    private String id;

    @Column (name = "first_name", nullable = false)
    private String firstName;

    @Column (name = "last_name", nullable = false)
    private String lastName;

    @Column (name = "created_at", nullable = false)
    @CreationTimestamp
    @Temporal (TemporalType.TIMESTAMP)
    private String createdAt;

    @Column (name = "modified_at")
    @UpdateTimestamp
    @Temporal (TemporalType.TIMESTAMP)
    private String modifiedAt;

}
