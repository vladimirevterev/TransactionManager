package ru.sberbank.transactionmanager.configuration.security;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

@Getter
@Setter
public class IdentifiedUser extends User {

    Long id;

    public IdentifiedUser(
            String username,
            String password,
            Long id,
            Collection<? extends GrantedAuthority> authorities
    ) {
        super(username, password, authorities);
        this.id = id;
    }
}
