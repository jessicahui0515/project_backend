package com.FSSE2510_project_jessica.FSSE2510_project.service.Impl;

import com.FSSE2510_project_jessica.FSSE2510_project.data.user.domainObject.FirebaseUserReqData;
import com.FSSE2510_project_jessica.FSSE2510_project.data.user.entity.UserEntity;
import com.FSSE2510_project_jessica.FSSE2510_project.mapper.user.UserEntityMapper;
import com.FSSE2510_project_jessica.FSSE2510_project.repository.UserRepository;
import com.FSSE2510_project_jessica.FSSE2510_project.service.UserService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserEntityMapper userEntityMapper;

    public UserServiceImpl(UserRepository userRepository, UserEntityMapper userEntityMapper) {
        this.userRepository = userRepository;
        this.userEntityMapper = userEntityMapper;
    }

    @Override
    public UserEntity getUserEntityByEmail(FirebaseUserReqData userData){
        Optional<UserEntity> userEntity = userRepository.findByEmail(userData.getEmail());

        return userEntity.orElseGet(() -> userRepository.save(userEntityMapper.toUserEntity(userData)));
    }
}
