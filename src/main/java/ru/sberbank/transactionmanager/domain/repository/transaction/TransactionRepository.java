package ru.sberbank.transactionmanager.domain.repository.transaction;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import ru.sberbank.transactionmanager.domain.entity.account.Account;
import ru.sberbank.transactionmanager.domain.entity.transaction.Transaction;

import java.util.List;

@Repository
public interface TransactionRepository extends PagingAndSortingRepository<Transaction, Long> {

    List<Transaction> findBySourceAccountInOrDestinationAccountIn(List<Account> sourceAccounts, List<Account> destinationAccounts);

}
