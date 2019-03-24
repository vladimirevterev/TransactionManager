package ru.sberbank.transactionmanager.domain.repository.user;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import ru.sberbank.transactionmanager.domain.entity.user.UserInfo;

import java.util.Optional;

@Repository
public interface UserInfoRepository extends PagingAndSortingRepository<UserInfo, Long> {

    Optional<UserInfo> findByLoginEquals(String login);

}
