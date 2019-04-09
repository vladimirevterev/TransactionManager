package ru.sberbank.transactionmanager.utils;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import ru.sberbank.transactionmanager.common.error.Error;
import ru.sberbank.transactionmanager.common.error.TransactionManagerException;
import ru.sberbank.transactionmanager.domain.entity.user.UserInfo;
import ru.sberbank.transactionmanager.domain.repository.user.UserInfoRepository;

import java.util.Objects;

/**
 * Вспомогательный класс для поиска пользователей
 */
@Component
@AllArgsConstructor
public class UserUtils {

    private final UserInfoRepository userInfoRepository;

    private final UserHelper userHelper;

    public void checkIfUserExists(Long userId) throws TransactionManagerException {
        ErrorHelper.check(
                Objects.isNull(userId) || !userInfoRepository.existsById(userId),
                "Информация о пользователе с идентификатором " + userId + " не найдена",
                Error.USER_NOT_FOUND
        );
    }

    public UserInfo getUserById(Long userId) throws TransactionManagerException {
        ErrorHelper.check(
                Objects.isNull(userId),
                "Информация о пользователе не найдена, т.к. не передан идентификатор пользователя",
                Error.USER_NOT_FOUND
        );
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
