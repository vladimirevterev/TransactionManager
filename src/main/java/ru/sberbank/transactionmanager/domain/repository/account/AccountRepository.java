package ru.sberbank.transactionmanager.domain.repository.account;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.sberbank.transactionmanager.domain.entity.account.Account;

@Repository
public interface AccountRepository extends CrudRepository<Account, Long> {
}
