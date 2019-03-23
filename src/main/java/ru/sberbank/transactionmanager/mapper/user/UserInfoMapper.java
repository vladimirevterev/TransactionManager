package ru.sberbank.transactionmanager.mapper.user;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;
import ru.sberbank.transactionmanager.domain.entity.user.UserInfo;
import ru.sberbank.transactionmanager.mapper.Mapper;
import ru.sberbank.transactionmanager.rest.dto.user.UserInfoDTO;

@Component
public class UserInfoMapper implements Mapper<UserInfo, UserInfoDTO> {

    @Override
    public UserInfo toEntity(UserInfoDTO userInfoDto) {
        UserInfo userInfo = new UserInfo();
        BeanUtils.copyProperties(userInfoDto, userInfo);
        return userInfo;
    }

    @Override
    public UserInfoDTO toDTO(UserInfo userInfo) {
        UserInfoDTO userInfoDto = new UserInfoDTO();
        BeanUtils.copyProperties(userInfo, userInfoDto);
        return userInfoDto;
    }

}
