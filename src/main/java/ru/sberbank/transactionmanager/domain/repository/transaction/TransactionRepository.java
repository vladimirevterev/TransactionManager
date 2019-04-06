package ru.sberbank.transactionmanager.domain.repository.transaction;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import ru.sberbank.transactionmanager.domain.entity.account.Account;
import ru.sberbank.transactionmanager.domain.entity.transaction.Transaction;

import java.util.List;

@Repository
public interface TransactionRepository extends PagingAndSortingRepository<Transaction, Long> {

    Page<Transaction> findTransactionBySourceAccountInOrDestinationAccountIn(
            List<Account> sourceAccounts,
            List<Account> destinationAccounts,
            Pageable pageable
    );

}
