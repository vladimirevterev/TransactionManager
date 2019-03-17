package ru.sberbank.transactionmanager.mapper;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;
import ru.sberbank.transactionmanager.domain.entity.user.UserInfo;
import ru.sberbank.transactionmanager.rest.dto.user.UserInfoDTO;

@Component
public class UserInfoMapper {

    // TODO: завести маперы. Сейчас не работают
    public UserInfo toEntity(UserInfoDTO userInfoDto) {
        UserInfo userInfo = new UserInfo();
        BeanUtils.copyProperties(userInfoDto, userInfo);
        return userInfo;
    }

    public UserInfoDTO toDto(UserInfo userInfo) {
        UserInfoDTO userInfoDto = new UserInfoDTO();
        BeanUtils.copyProperties(userInfo, userInfoDto);
        return userInfoDto;
    }

}
