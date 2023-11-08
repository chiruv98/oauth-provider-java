package com.oauthprovider.repositories;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

import com.oauthprovider.entities.UserEntity;

public interface UserRepository extends JpaRepository <UserEntity, UUID>{

}
