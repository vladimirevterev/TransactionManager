package ru.sberbank.transactionmanager.mapper.user;

import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import ru.sberbank.transactionmanager.domain.entity.user.UserInfo;
import ru.sberbank.transactionmanager.mapper.Mapper;
import ru.sberbank.transactionmanager.mapper.account.AccountMapper;
import ru.sberbank.transactionmanager.rest.dto.user.UserInfoDTO;

import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class UserInfoMapper implements Mapper<UserInfo, UserInfoDTO> {

    private RoleMapper roleMapper;

    private AccountMapper accountMapper;

    @Override
    public UserInfo toEntity(UserInfoDTO userInfoDto) {
        UserInfo userInfo = new UserInfo();
        BeanUtils.copyProperties(userInfoDto, userInfo, "roles", "accounts");
        if (!CollectionUtils.isEmpty(userInfoDto.getRoles())) {
            userInfo.setRoles(
                    userInfoDto.getRoles().stream().map(roleMapper::toEntity).collect(Collectors.toList())
            );
        }
        if (!CollectionUtils.isEmpty(userInfo.getAccounts())) {
            userInfo.setAccounts(
                    userInfoDto.getAccounts().stream().map(accountMapper::toEntity).collect(Collectors.toList())
            );
        }
        return userInfo;
    }

    @Override
    public UserInfoDTO toDTO(UserInfo userInfo) {
        UserInfoDTO userInfoDTO = new UserInfoDTO();
        BeanUtils.copyProperties(userInfo, userInfoDTO, "roles", "accounts");
        if (!CollectionUtils.isEmpty(userInfo.getRoles())) {
            userInfoDTO.setRoles(
                    userInfo.getRoles().stream().map(roleMapper::toDTO).collect(Collectors.toList())
            );
        }
        if (!CollectionUtils.isEmpty(userInfo.getAccounts())) {
            userInfoDTO.setAccounts(
                    userInfo.getAccounts().stream().map(accountMapper::toDTO).collect(Collectors.toList())
            );
        }
        return userInfoDTO;
    }

}
