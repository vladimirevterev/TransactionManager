package ru.sberbank.transactionmanager.domain.repository.user;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import ru.sberbank.transactionmanager.domain.entity.user.UserInfo;
import ru.sberbank.transactionmanager.domain.entity.user.UserRole;

import java.util.List;

@Repository
public interface UserRoleRepository extends PagingAndSortingRepository<UserRole, Long> {

    List<UserRole> findByUserInfoEquals(UserInfo user);

}
