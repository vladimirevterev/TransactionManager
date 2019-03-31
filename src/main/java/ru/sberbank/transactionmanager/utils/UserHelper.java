package ru.sberbank.transactionmanager.utils;

import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import ru.sberbank.transactionmanager.configuration.security.IdentifiedUser;
import ru.sberbank.transactionmanager.domain.entity.user.UserInfo;
import ru.sberbank.transactionmanager.domain.repository.user.UserInfoRepository;

import java.util.Objects;
import java.util.Optional;

/**
 * Вспомогательный класс для работы с текущим пользователем
 */
@Component
@AllArgsConstructor
public class UserHelper {

    private UserInfoRepository userInfoRepository;

    public UserInfo getCurrentUser() {
        IdentifiedUser user = (IdentifiedUser) Optional.ofNullable(SecurityContextHolder.getContext().getAuthentication())
                .map(Authentication::getPrincipal)
                .orElse(null);
        if (Objects.isNull(user)) {
            return null;
        }
        return userInfoRepository.findById(user.getId()).orElse(null);
    }

}
