package com.example.railway.dao;

import com.example.railway.domain.entity.user.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserDao extends JpaRepository<UserEntity, UUID> {
    Optional<UserEntity> findUserEntitiesByEmail (String email);

}
