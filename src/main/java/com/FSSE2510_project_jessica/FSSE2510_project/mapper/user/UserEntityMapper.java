package com.FSSE2510_project_jessica.FSSE2510_project.mapper.user;

import com.FSSE2510_project_jessica.FSSE2510_project.data.user.domainObject.FirebaseUserReqData;
import com.FSSE2510_project_jessica.FSSE2510_project.data.user.entity.UserEntity;
import org.springframework.stereotype.Component;

@Component
public class UserEntityMapper {

    public UserEntity toUserEntity (FirebaseUserReqData userData){
        UserEntity userEntity = new UserEntity();
        userEntity.setEmail(userData.getEmail());
        userEntity.setFirebaseUid(userData.getFirebaseUid());
        return userEntity;
    }


}
