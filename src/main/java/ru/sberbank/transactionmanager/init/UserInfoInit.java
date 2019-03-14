package ru.sberbank.transactionmanager.init;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import ru.sberbank.transactionmanager.domain.UserInfo;
import ru.sberbank.transactionmanager.repository.UserInfoRepository;

import java.time.LocalDate;

@Component
@AllArgsConstructor
public class UserInfoInit implements ApplicationRunner {

    @Autowired
    private UserInfoRepository userInfoRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        generateUserInfoData();
    }

    private void generateUserInfoData() {
        UserInfo userInfo = UserInfo.builder()
                .firstName("Vladimir")
                .lastName("Evterev")
                .middleName("Michailovich")
                .birthDate(LocalDate.of(1994, 12, 15))
                .build();
        userInfoRepository.save(userInfo);
    }
}
