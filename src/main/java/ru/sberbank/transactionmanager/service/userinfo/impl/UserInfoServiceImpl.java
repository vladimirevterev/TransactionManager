package ru.sberbank.transactionmanager.service.userinfo.impl;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.sberbank.transactionmanager.dto.UserInfoDto;
import ru.sberbank.transactionmanager.mapper.UserInfoMapper;
import ru.sberbank.transactionmanager.repository.UserInfoRepository;
import ru.sberbank.transactionmanager.service.userinfo.UserInfoService;

@AllArgsConstructor
@Component
public class UserInfoServiceImpl implements UserInfoService {

    @Autowired
    private UserInfoMapper userInfoMapper;

    @Autowired
    private UserInfoRepository userInfoRepository;

    @Override
    public UserInfoDto getUserInfo(Long userId) {
        return userInfoRepository.findById(userId).map(ui -> userInfoMapper.toDto(ui)).orElse(null);
    }

}
