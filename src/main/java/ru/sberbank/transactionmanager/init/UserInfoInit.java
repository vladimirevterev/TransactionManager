package ru.sberbank.transactionmanager.init;

import lombok.AllArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import ru.sberbank.transactionmanager.domain.entity.account.Account;
import ru.sberbank.transactionmanager.domain.entity.user.Role;
import ru.sberbank.transactionmanager.domain.entity.user.UserInfo;
import ru.sberbank.transactionmanager.domain.entity.user.UserRole;
import ru.sberbank.transactionmanager.domain.repository.account.AccountRepository;
import ru.sberbank.transactionmanager.domain.repository.user.RoleRepository;
import ru.sberbank.transactionmanager.domain.repository.user.UserInfoRepository;
import ru.sberbank.transactionmanager.domain.repository.user.UserRoleRepository;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

@Component
@AllArgsConstructor
@Setter
public class UserInfoInit implements ApplicationRunner {

    private static final String ROLE_ANONYMOUS_CODE = "01";
    private static final String ROLE_USER_CODE = "02";
    private static final String ROLE_ADMIN_CODE = "03";

    private static final String DEFAULT_PASSWORD = "123";

    @Autowired
    BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private UserInfoRepository userInfoRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserRoleRepository userRoleRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        generateUserInfoData();
    }

    private void generateUserInfoData() {
        fillRoles();
        UserInfo userInfo = UserInfo.builder()
                .login("Evterev-VM")
                .firstName("Vladimir")
                .encryptedPassword(passwordEncoder.encode(DEFAULT_PASSWORD))
                .lastName("Evterev")
                .middleName("Michailovich")
                .birthDate(LocalDate.of(1994, 12, 15))
                .build();
        userInfo = userInfoRepository.save(userInfo);
        setUserRoles(userInfo, (List<Role>) roleRepository.findAll());
        generateUserAccount(userInfo, true);
        generateUserAccount(userInfo, false);
    }

    private void fillRoles() {
        Role anonymousRole = Role.builder()
                .code(ROLE_ANONYMOUS_CODE)
                .name("ROLE_ANONYMOUS")
                .build();
        Role userRole = Role.builder()
                .code(ROLE_USER_CODE)
                .name("ROLE_USER")
                .build();
        Role adminRole = Role.builder()
                .code(ROLE_ADMIN_CODE)
                .name("ROLE_ADMIN")
                .build();
        roleRepository.save(anonymousRole);
        roleRepository.save(userRole);
        roleRepository.save(adminRole);
    }

    private void setUserRoles(UserInfo userInfo, List<Role> roles) {
        roles.forEach(role ->
                userRoleRepository.save(UserRole.builder().userInfo(userInfo).role(role).build())
        );
    }

    private void generateUserAccount(UserInfo userInfo, Boolean isPrimary) {
        Account account = Account.builder()
                .balance(new Random().nextDouble() * 10000D)
                .isPrimary(isPrimary)
                .userInfo(userInfo)
                .build();
        accountRepository.save(account);
    }
}
