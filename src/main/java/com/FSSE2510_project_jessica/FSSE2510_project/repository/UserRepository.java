package com.FSSE2510_project_jessica.FSSE2510_project.repository;

import com.FSSE2510_project_jessica.FSSE2510_project.data.user.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<UserEntity, Integer> {
    Optional<UserEntity> findByEmail (String email);
}
