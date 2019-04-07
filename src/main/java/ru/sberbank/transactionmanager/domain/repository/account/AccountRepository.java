package ru.sberbank.transactionmanager.domain.repository.account;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import ru.sberbank.transactionmanager.domain.entity.account.Account;
import ru.sberbank.transactionmanager.domain.entity.user.UserInfo;

@Repository
public interface AccountRepository extends PagingAndSortingRepository<Account, Long> {

    Page<Account> findAccountByUserInfo(UserInfo userInfo, Pageable pageable);

}
