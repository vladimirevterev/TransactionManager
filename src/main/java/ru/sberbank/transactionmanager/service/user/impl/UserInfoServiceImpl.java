package ru.sberbank.transactionmanager.service.user.impl;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import ru.sberbank.transactionmanager.common.error.Error;
import ru.sberbank.transactionmanager.common.error.TransactionManagerException;
import ru.sberbank.transactionmanager.domain.entity.user.UserInfo;
import ru.sberbank.transactionmanager.domain.entity.user.UserRole;
import ru.sberbank.transactionmanager.domain.repository.account.AccountRepository;
import ru.sberbank.transactionmanager.domain.repository.transaction.TransactionRepository;
import ru.sberbank.transactionmanager.domain.repository.user.UserRoleRepository;
import ru.sberbank.transactionmanager.mapper.account.AccountMapper;
import ru.sberbank.transactionmanager.mapper.user.RoleMapper;
import ru.sberbank.transactionmanager.rest.dto.account.AccountDTO;
import ru.sberbank.transactionmanager.rest.dto.user.UserInfoDTO;
import ru.sberbank.transactionmanager.mapper.user.UserInfoMapper;
import ru.sberbank.transactionmanager.domain.repository.user.UserInfoRepository;
import ru.sberbank.transactionmanager.service.user.UserInfoService;
import ru.sberbank.transactionmanager.utils.ErrorHelper;
import ru.sberbank.transactionmanager.utils.UserUtils;

@AllArgsConstructor
@Service
public class UserInfoServiceImpl implements UserInfoService {

    private static final String DEFAULT_PASSWORD = "123";

    private UserInfoMapper userInfoMapper;

    private RoleMapper roleMapper;

    private AccountMapper accountMapper;

    private UserInfoRepository userInfoRepository;

    private UserRoleRepository userRoleRepository;

    private TransactionRepository transactionRepository;

    private AccountRepository accountRepository;

    private BCryptPasswordEncoder bCryptPasswordEncoder;

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
        UserInfo userInfo = userInfoMapper.toEntity(userInfoDTO);
        userInfo.setEncryptedPassword(bCryptPasswordEncoder.encode(DEFAULT_PASSWORD));
        userInfo = userInfoRepository.save(userInfo);
        updateUserRoles(userInfo);
        return userInfoMapper.toDTO(userInfo);
    }

    @Override
    @Transactional
    public UserInfoDTO updateUser(UserInfoDTO userInfoDTO) throws TransactionManagerException {
        UserInfo oldUserInfo = userUtils.getUserById(userInfoDTO.getId());
        UserInfo newUserInfo = userInfoMapper.toEntity(userInfoDTO);
        newUserInfo.setEncryptedPassword(oldUserInfo.getEncryptedPassword());
        newUserInfo = userInfoRepository.save(userInfoMapper.toEntity(userInfoDTO));
        updateUserRoles(newUserInfo);
        return userInfoMapper.toDTO(newUserInfo);
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
        userRoleRepository.deleteUserRoleByUserInfoEquals(userInfo);
        transactionRepository.deleteTransactionByDestinationAccountIn(userInfo.getAccounts());
        transactionRepository.deleteTransactionBySourceAccountIn(userInfo.getAccounts());
        accountRepository.deleteAccountByUserInfoEquals(userInfo);
        userInfoRepository.delete(userInfo);
    }

    private void updateUserRoles(UserInfo userInfo) {
        userRoleRepository.deleteUserRoleByUserInfoEquals(userInfo);
        if (CollectionUtils.isEmpty(userInfo.getRoles())) {
            return;
        }
        userInfo.getRoles().forEach(role ->
            userRoleRepository.save(UserRole.builder().role(role).userInfo(userInfo).build())
        );
    }
}
