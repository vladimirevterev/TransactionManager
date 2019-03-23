package ru.sberbank.transactionmanager.domain.repository.transaction;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.sberbank.transactionmanager.domain.entity.account.Account;
import ru.sberbank.transactionmanager.domain.entity.transaction.TransactionHistory;

import java.util.List;

@Repository
public interface TransactionHistoryRepository extends JpaRepository<TransactionHistory, Long> {

    List<TransactionHistory> findBySourceAccountInOrDestinationAccountIn(List<Account> sourceAccounts, List<Account> destinationAccounts);

}
