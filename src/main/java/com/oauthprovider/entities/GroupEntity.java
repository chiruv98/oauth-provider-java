package com.oauthprovider.entities;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Data;

/*
 * @author Chiranjeevi
 */

@Entity
@Table (name = "group", catalog = "oauthdb")
@Data
public class GroupEntity {
    
    @Id
    @Column (name = "id", updatable = false, nullable = false)
    @GeneratedValue (strategy = GenerationType.UUID)
    private String id;

    @Column (name = "group_name")
    private String groupName;

    @Column (name = "description")
    private String description;

    @Column (name = "created_at", updatable = false, nullable = false)
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private String createdAt;

    @Column (name = "modified_at")
    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private String modifiedAt;

}
