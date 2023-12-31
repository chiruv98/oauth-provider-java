package com.oauthprovider.repositories;

import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

import com.oauthprovider.entities.GroupEntity;

/*
 * @author Chiranjeevi
 */

public interface GroupRepository extends JpaRepository <GroupEntity, UUID>{

    Optional<GroupEntity> findById(String id);
    Optional<GroupEntity> findByGroupName(String name);
}
