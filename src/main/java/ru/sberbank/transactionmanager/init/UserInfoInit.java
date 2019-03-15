package ru.sberbank.transactionmanager.init;

import lombok.AllArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import ru.sberbank.transactionmanager.domain.account.Account;
import ru.sberbank.transactionmanager.domain.userinfo.UserInfo;
import ru.sberbank.transactionmanager.repository.AccountRepository;
import ru.sberbank.transactionmanager.repository.UserInfoRepository;

import java.time.LocalDate;

@Component
@AllArgsConstructor
@Setter
public class UserInfoInit implements ApplicationRunner {

    @Autowired
    private UserInfoRepository userInfoRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        generateUserInfoData();
    }

    private void generateUserInfoData() {
        UserInfo userInfo = UserInfo.builder()
                .login("Evterev-VM")
                .firstName("Vladimir")
                .lastName("Evterev")
                .middleName("Michailovich")
                .birthDate(LocalDate.of(1994, 12, 15))
                .build();
        userInfo = userInfoRepository.save(userInfo);
        generateAccountData(userInfo, true);
        generateAccountData(userInfo, false);
    }

    private void generateAccountData(UserInfo userInfo, Boolean isPrimary) {
        Account account = Account.builder()
                .balance(1000.10D)
                .isPrimary(isPrimary)
                .userInfo(userInfo)
                .build();
        accountRepository.save(account);
    }
}
