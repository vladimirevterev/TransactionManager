package ru.sberbank.transactionmanager.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.sberbank.transactionmanager.domain.account.Account;

@Repository
public interface AccountRepository extends CrudRepository<Account, Long> {
}
