package com.oauthprovider.repositories;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.oauthprovider.entities.UserEntity;

public interface UserRepository extends JpaRepository <UserEntity, UUID>{

    Optional<UserEntity> findByEmail(String email);
    Optional<List<UserEntity>> findByGroupId(String groupId);

}
