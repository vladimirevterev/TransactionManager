package ru.sberbank.transactionmanager.init;

import lombok.AllArgsConstructor;
import lombok.Setter;
import org.apache.commons.lang3.RandomStringUtils;
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
import java.time.Period;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

@Component
@AllArgsConstructor
@Setter
public class DBInit implements ApplicationRunner {

    private static final String DEFAULT_PASSWORD = "123";

    private static final Integer MAX_USER_COUNT = 20;

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
        List<UserInfo> users = generateUsers();
        List<Account> accounts = generateAccounts(users);
    }

    private List<UserInfo> generateUsers() {
        List<UserInfo> users = new ArrayList<>();
        users.add(generateAdminUser());
        users.add(generateSystemUser());
        users.add(generateOrdinarUser());
        int userCount = new Random().nextInt(MAX_USER_COUNT);
        for (int i = 0; i < userCount; ++i) {
            users.add(generateRandomUser());
        }
        return users;
    }

    private UserInfo generateAdminUser() {
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
        return adminUserInfo;
    }

    private UserInfo generateSystemUser() {
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
        return sysUserInfo;
    }

    private UserInfo generateOrdinarUser() {
        UserInfo ordinarUser = UserInfo.builder()
                .login("user")
                .encryptedPassword(passwordEncoder.encode(DEFAULT_PASSWORD))
                .firstName("Пользователь")
                .lastName("Пользователь")
                .birthDate(LocalDate.of(1970, 1, 1))
                .build();
        ordinarUser = userInfoRepository.save(ordinarUser);
        setUserRoles(
                ordinarUser,
                Collections.singletonList(roleRepository.findRoleByCode(RoleDictionary.USER.getCode()))
        );
        return ordinarUser;
    }

    private UserInfo generateRandomUser() {
        String userName = RandomStringUtils.randomAlphabetic(15);
        UserInfo randomUser = UserInfo.builder()
                .login(userName.toLowerCase())
                .encryptedPassword(passwordEncoder.encode(DEFAULT_PASSWORD))
                .firstName(userName)
                .lastName(userName)
                .birthDate(LocalDate.now().minus(Period.ofDays((ThreadLocalRandom.current().nextInt(365 * 70)))))
                .build();
        randomUser = userInfoRepository.save(randomUser);
        setUserRoles(
                randomUser,
                Collections.singletonList(roleRepository.findRoleByCode(RoleDictionary.USER.getCode()))
        );
        return randomUser;
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

    private List<Account> generateAccounts(List<UserInfo> users) {
        List<Account> accounts = new ArrayList<>();
        for (UserInfo user : users ) {
            accounts.add(generateUserAccount(user, true));
            accounts.add(generateUserAccount(user, false));
        }
        return accounts;
    }

    private Account generateUserAccount(UserInfo userInfo, Boolean isPrimary) {
        Account account = Account.builder()
                .balance(new Random().nextDouble() * 10000D)
                .isPrimary(isPrimary)
                .userInfo(userInfo)
                .build();
        return accountRepository.save(account);
    }
}
