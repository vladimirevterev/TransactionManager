package ru.sberbank.transactionmanager.configuration.auditor;

import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import ru.sberbank.transactionmanager.configuration.security.IdentifiedUser;

import java.util.Optional;

public class AuditorAwareImpl implements AuditorAware<Long> {

    @Override
    public Optional<Long> getCurrentAuditor() {
        IdentifiedUser user = (IdentifiedUser) Optional.ofNullable(SecurityContextHolder.getContext().getAuthentication())
                .map(Authentication::getPrincipal)
                .orElse(null);
        return Optional.ofNullable(user).map(IdentifiedUser::getId);
    }

}
