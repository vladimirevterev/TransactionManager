package ru.sberbank.transactionmanager.service.userinfo.impl;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.sberbank.transactionmanager.dto.user.UserInfoDto;
import ru.sberbank.transactionmanager.mapper.UserInfoMapper;
import ru.sberbank.transactionmanager.domain.repository.user.UserInfoRepository;
import ru.sberbank.transactionmanager.service.userinfo.UserInfoService;

@AllArgsConstructor
@Service
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
