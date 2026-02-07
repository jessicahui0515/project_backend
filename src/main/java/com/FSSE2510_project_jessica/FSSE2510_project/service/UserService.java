package com.FSSE2510_project_jessica.FSSE2510_project.service;

import com.FSSE2510_project_jessica.FSSE2510_project.data.user.domainObject.FirebaseUserReqData;
import com.FSSE2510_project_jessica.FSSE2510_project.data.user.entity.UserEntity;

public interface UserService {
    UserEntity getUserEntityByEmail(FirebaseUserReqData userData);
}
