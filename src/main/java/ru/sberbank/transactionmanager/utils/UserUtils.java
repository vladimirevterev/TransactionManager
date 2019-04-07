package ru.sberbank.transactionmanager.utils;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import ru.sberbank.transactionmanager.common.error.Error;
import ru.sberbank.transactionmanager.common.error.TransactionManagerException;
import ru.sberbank.transactionmanager.domain.entity.user.UserInfo;
import ru.sberbank.transactionmanager.domain.repository.user.UserInfoRepository;

import java.util.Objects;

@Component
@AllArgsConstructor
public class UserUtils {

    private final UserInfoRepository userInfoRepository;

    private final UserHelper userHelper;

    public UserInfo getUserById(Long userId) throws TransactionManagerException {
        UserInfo userInfo = userInfoRepository.findById(userId).orElse(null);
        ErrorHelper.check(
                Objects.isNull(userInfo),
                "Информация о пользователе с идентификатором " + userId + " не найдена",
                Error.USER_NOT_FOUND
        );
        return userInfo;
    }

    public UserInfo getCurrentUser() throws TransactionManagerException {
        UserInfo currentUserInfo = userHelper.getCurrentUser();
        ErrorHelper.check(
                Objects.isNull(currentUserInfo),
                "Текущий пользователь не задан",
                Error.CURRENT_USER_NOT_SPECIFIED
        );
        return currentUserInfo;
    }

}
