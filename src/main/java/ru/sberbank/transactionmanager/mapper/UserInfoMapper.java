package ru.sberbank.transactionmanager.mapper;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;
import ru.sberbank.transactionmanager.domain.UserInfo;
import ru.sberbank.transactionmanager.dto.UserInfoDto;

@Component
public class UserInfoMapper {

    public UserInfo toEntity(UserInfoDto userInfoDto) {
        UserInfo userInfo = new UserInfo();
        BeanUtils.copyProperties(userInfoDto, userInfo);
        return userInfo;
    }

    public UserInfoDto toDto(UserInfo userInfo) {
        UserInfoDto userInfoDto = new UserInfoDto();
        BeanUtils.copyProperties(userInfo, userInfoDto);
        return userInfoDto;
    }

}
