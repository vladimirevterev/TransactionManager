package ru.sberbank.transactionmanager.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.sberbank.transactionmanager.domain.userinfo.UserInfo;

@Repository
public interface UserInfoRepository extends CrudRepository<UserInfo, Long> {
}
