package ru.sberbank.transactionmanager.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.sberbank.transactionmanager.domain.account.Account;
import ru.sberbank.transactionmanager.domain.transaction.TransactionHistory;

import java.util.List;

@Repository
public interface TransactionHistoryRepository extends CrudRepository<TransactionHistory, Long> {

    List<TransactionHistory> findBySourceAccountInOrDestinationAccountIn(List<Account> sourceAccounts, List<Account> destinationAccounts);

}
