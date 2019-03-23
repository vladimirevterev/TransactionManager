package ru.sberbank.transactionmanager.service.user.impl;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.sberbank.transactionmanager.domain.entity.user.UserInfo;
import ru.sberbank.transactionmanager.rest.dto.user.UserInfoDTO;
import ru.sberbank.transactionmanager.mapper.user.UserInfoMapper;
import ru.sberbank.transactionmanager.domain.repository.user.UserInfoRepository;
import ru.sberbank.transactionmanager.service.user.UserInfoService;

import java.util.Objects;

@AllArgsConstructor
@Service
public class UserInfoServiceImpl implements UserInfoService {

    private UserInfoMapper userInfoMapper;

    private UserInfoRepository userInfoRepository;

    @Override
    public Page<UserInfoDTO> getUsers(Pageable pageable) {
        return userInfoRepository.findAll(pageable).map(userInfoMapper::toDTO);
    }

    @Override
    @Transactional
    public UserInfoDTO createUser(UserInfoDTO userInfoDTO) {
        return userInfoMapper.toDTO(userInfoRepository.save(userInfoMapper.toEntity(userInfoDTO)));
    }

    @Override
    public UserInfoDTO getUser(Long userId) {
        return userInfoRepository.findById(userId).map(userInfoMapper::toDTO)
                .orElse(null);
    }

    @Override
    @Transactional
    public UserInfoDTO updateUser(UserInfoDTO userInfoDTO) {
        return userInfoMapper.toDTO(userInfoRepository.save(userInfoMapper.toEntity(userInfoDTO)));
    }

    @Override
    @Transactional
    public void deleteUser(Long userId) {
        UserInfo userInfo = userInfoRepository.findById(userId).orElse(null);
        if (Objects.nonNull(userInfo)) {
            userInfoRepository.delete(userInfo);
        }
    }

}
