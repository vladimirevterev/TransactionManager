package ru.sberbank.transactionmanager.configuration.auditor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import ru.sberbank.transactionmanager.domain.entity.user.UserInfo;
import ru.sberbank.transactionmanager.domain.repository.user.UserInfoRepository;

import java.util.Objects;
import java.util.Optional;

public class AuditorAwareImpl implements AuditorAware<Long> {

    @Autowired
    UserInfoRepository userInfoRepository;

    @Override
    public Optional<Long> getCurrentAuditor() {
        User user = (User) Optional.ofNullable(SecurityContextHolder.getContext().getAuthentication())
                .map(Authentication::getPrincipal)
                .orElse(null);
        if (Objects.isNull(user)) {
            return Optional.empty();
        }
        return userInfoRepository.findByLoginEquals(user.getUsername()).map(UserInfo::getId);
    }
}
