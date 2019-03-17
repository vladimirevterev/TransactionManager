package ru.sberbank.transactionmanager.service.user.impl;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.sberbank.transactionmanager.domain.entity.user.UserInfo;
import ru.sberbank.transactionmanager.rest.dto.user.UserInfoDTO;
import ru.sberbank.transactionmanager.mapper.UserInfoMapper;
import ru.sberbank.transactionmanager.domain.repository.user.UserInfoRepository;
import ru.sberbank.transactionmanager.service.user.UserInfoService;

@AllArgsConstructor
@Service
public class UserInfoServiceImpl implements UserInfoService {

    @Autowired
    private UserInfoMapper userInfoMapper;

    @Autowired
    private UserInfoRepository userInfoRepository;

    @Override
    public UserInfoDTO getUserInfo(Long userId) {
        return userInfoRepository.findById(userId).map(ui -> userInfoMapper.toDto(ui)).orElse(null);
    }

    @Override
    public UserInfoDTO createUserInfo(UserInfoDTO userInfoDTO) {
        return userInfoMapper.toDto(userInfoRepository.save(userInfoMapper.toEntity(userInfoDTO)));
    }

}
