package ru.sberbank.transactionmanager.service.user.impl;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.sberbank.transactionmanager.common.error.Error;
import ru.sberbank.transactionmanager.common.error.TransactionManagerException;
import ru.sberbank.transactionmanager.domain.entity.user.UserInfo;
import ru.sberbank.transactionmanager.domain.repository.account.AccountRepository;
import ru.sberbank.transactionmanager.mapper.account.AccountMapper;
import ru.sberbank.transactionmanager.rest.dto.account.AccountDTO;
import ru.sberbank.transactionmanager.rest.dto.user.UserInfoDTO;
import ru.sberbank.transactionmanager.mapper.user.UserInfoMapper;
import ru.sberbank.transactionmanager.domain.repository.user.UserInfoRepository;
import ru.sberbank.transactionmanager.service.user.UserInfoService;
import ru.sberbank.transactionmanager.utils.ErrorHelper;
import ru.sberbank.transactionmanager.utils.UserUtils;

import java.util.Objects;

@AllArgsConstructor
@Service
public class UserInfoServiceImpl implements UserInfoService {

    private UserInfoMapper userInfoMapper;

    private AccountMapper accountMapper;

    private UserInfoRepository userInfoRepository;

    private AccountRepository accountRepository;

    private UserUtils userUtils;

    @Override
    @Transactional
    public UserInfoDTO getUser(Long userId) throws TransactionManagerException {
        return userInfoMapper.toDTO(userUtils.getUserById(userId));
    }

    @Override
    @Transactional
    public UserInfoDTO getCurrentUser() throws TransactionManagerException {
        return userInfoMapper.toDTO(userUtils.getCurrentUser());
    }

    @Override
    @Transactional
    public Page<UserInfoDTO> getUsers(Pageable pageable) {
        return userInfoRepository.findAll(pageable).map(userInfoMapper::toDTO);
    }

    @Override
    public Page<AccountDTO> getUserAccounts(Long userId, Pageable pageable) throws TransactionManagerException {
        return accountRepository.findAccountByUserInfo(userUtils.getUserById(userId), pageable).map(accountMapper::toDTO);
    }

    @Override
    public Page<AccountDTO> getCurrenUserAccounts(Pageable pageable) throws TransactionManagerException {
        return accountRepository.findAccountByUserInfo(userUtils.getCurrentUser(), pageable).map(accountMapper::toDTO);
    }

    @Override
    @Transactional
    public UserInfoDTO createUser(UserInfoDTO userInfoDTO) {
        return userInfoMapper.toDTO(userInfoRepository.save(userInfoMapper.toEntity(userInfoDTO)));
    }

    @Override
    @Transactional
    public UserInfoDTO updateUser(UserInfoDTO userInfoDTO) {
        return userInfoMapper.toDTO(userInfoRepository.save(userInfoMapper.toEntity(userInfoDTO)));
    }

    @Override
    @Transactional
    public void deleteUser(Long userId) throws TransactionManagerException {
        if (userUtils.getCurrentUser().getId().equals(userId)) {
            ErrorHelper.makeError(
                    "Удаление текущего пользователя запрещено",
                    Error.REMOVING_USER_IS_FORBIDDEN
            );
        }
        UserInfo userInfo = userUtils.getUserById(userId);
        userInfoRepository.delete(userInfo);
    }

}
