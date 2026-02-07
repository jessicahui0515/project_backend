package com.FSSE2510_project_jessica.FSSE2510_project.repository;

import com.FSSE2510_project_jessica.FSSE2510_project.data.cartItem.entity.CartItemEntity;
import com.FSSE2510_project_jessica.FSSE2510_project.data.user.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface CartItemRepository extends CrudRepository<CartItemEntity, Integer> {
    Optional<CartItemEntity> findByProduct_PidAndUser_Uid(Integer pid, Integer uid);
    List<CartItemEntity> findAllByUser(UserEntity userEntity);
    void deleteByProduct_PidAndUser(Integer pid, UserEntity userEntity);

    UserEntity user(UserEntity user);
}
