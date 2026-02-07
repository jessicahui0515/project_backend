package com.FSSE2510_project_jessica.FSSE2510_project.mapper.user;

import com.FSSE2510_project_jessica.FSSE2510_project.data.user.domainObject.FirebaseUserReqData;
import com.FSSE2510_project_jessica.FSSE2510_project.data.user.domainObject.UserRespData;
import com.FSSE2510_project_jessica.FSSE2510_project.data.user.entity.UserEntity;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Component;

@Component
public class UserDataMapper {
    public FirebaseUserReqData toFirebaseUserReqData(Jwt jwt){
        FirebaseUserReqData reqData = new FirebaseUserReqData();
        reqData.setFirebaseUid(jwt.getClaimAsString("user_id"));
        reqData.setEmail(jwt.getClaimAsString("email"));
        return reqData;
    }

    public UserRespData toUserRespData (UserEntity userEntity){
        UserRespData respData = new UserRespData();
        respData.setEmail(userEntity.getEmail());
        respData.setUid(userEntity.getUid());
        respData.setFirebaseUid(userEntity.getFirebaseUid());
        return  respData;
    }

}
