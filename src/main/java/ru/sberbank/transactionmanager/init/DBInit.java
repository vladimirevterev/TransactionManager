package ru.sberbank.transactionmanager.init;

import lombok.AllArgsConstructor;
import lombok.Setter;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import ru.sberbank.transactionmanager.common.dictionary.RoleDictionary;
import ru.sberbank.transactionmanager.common.dictionary.TransactionTypeDictionary;
import ru.sberbank.transactionmanager.domain.entity.account.Account;
import ru.sberbank.transactionmanager.domain.entity.transaction.TransactionType;
import ru.sberbank.transactionmanager.domain.entity.user.Role;
import ru.sberbank.transactionmanager.domain.entity.user.UserInfo;
import ru.sberbank.transactionmanager.domain.entity.user.UserRole;
import ru.sberbank.transactionmanager.domain.repository.account.AccountRepository;
import ru.sberbank.transactionmanager.domain.repository.transaction.TransactionTypeRepository;
import ru.sberbank.transactionmanager.domain.repository.user.RoleRepository;
import ru.sberbank.transactionmanager.domain.repository.user.UserInfoRepository;
import ru.sberbank.transactionmanager.domain.repository.user.UserRoleRepository;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Random;

@Component
@AllArgsConstructor
@Setter
public class DBInit implements ApplicationRunner {

    private static final String DEFAULT_PASSWORD = "123";

    BCryptPasswordEncoder passwordEncoder;

    private UserInfoRepository userInfoRepository;

    private AccountRepository accountRepository;

    private RoleRepository roleRepository;

    private UserRoleRepository userRoleRepository;

    private TransactionTypeRepository transactionTypeRepository;

    @Override
    public void run(ApplicationArguments args) {
        generateDBData();
    }

    private void generateDBData() {
        fillDictionaries();
        UserInfo adminUserInfo = UserInfo.builder()
                .login("admin")
                .encryptedPassword(passwordEncoder.encode(DEFAULT_PASSWORD))
                .firstName("Администратор")
                .lastName("Администратор")
                .birthDate(LocalDate.of(1970, 1, 1))
                .build();
        adminUserInfo = userInfoRepository.save(adminUserInfo);
        setUserRoles(
                adminUserInfo,
                Collections.singletonList(roleRepository.findRoleByCode(RoleDictionary.ADMIN.getCode()))
        );
        UserInfo sysUserInfo = UserInfo.builder()
                .login("sys")
                .encryptedPassword(passwordEncoder.encode(DEFAULT_PASSWORD))
                .firstName("Система")
                .lastName("Система")
                .birthDate(LocalDate.of(1970, 1, 1))
                .build();
        sysUserInfo = userInfoRepository.save(sysUserInfo);
        setUserRoles(
                sysUserInfo,
                Collections.singletonList(roleRepository.findRoleByCode(RoleDictionary.SYSTEM.getCode()))
        );
        generateUserAccount(adminUserInfo, true);
        generateUserAccount(adminUserInfo, false);
        generateUserAccount(sysUserInfo, true);
        generateUserAccount(sysUserInfo, false);
    }

    private void fillDictionaries() {
        fillRoles();
        fillTransactionTypes();
    }

    private void fillRoles() {
        for (RoleDictionary rd : RoleDictionary.values()) {
            roleRepository.save(
                    Role.builder()
                            .code(rd.getCode())
                            .name(RoleDictionary.ROLE_PREFIX + rd.toString())
                            .build()
            );
        }
    }

    private void fillTransactionTypes() {
        for (TransactionTypeDictionary ttd : TransactionTypeDictionary.values()) {
            transactionTypeRepository.save(
                    TransactionType.builder()
                            .code(ttd.getCode())
                            .name(ttd.toString())
                            .description(ttd.getDescription())
                            .build()
            );
        }
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
