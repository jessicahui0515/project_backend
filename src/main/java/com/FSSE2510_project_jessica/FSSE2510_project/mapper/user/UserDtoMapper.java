package com.FSSE2510_project_jessica.FSSE2510_project.mapper.user;

import com.FSSE2510_project_jessica.FSSE2510_project.data.user.Dto.UserRespDto;
import com.FSSE2510_project_jessica.FSSE2510_project.data.user.domainObject.UserRespData;
import org.springframework.stereotype.Component;

@Component
public class UserDtoMapper {
    public UserRespDto toUserRespDto(UserRespData respData){
        UserRespDto respDto = new UserRespDto();
        respDto.setEmail(respData.getEmail());
        respDto.setUid(respData.getUid());
        respDto.setFirebaseUid(respData.getFirebaseUid());
        return respDto;
    }
}
