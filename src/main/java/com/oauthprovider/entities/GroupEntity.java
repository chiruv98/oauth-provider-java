package com.oauthprovider.entities;

import java.util.UUID;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
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

}
