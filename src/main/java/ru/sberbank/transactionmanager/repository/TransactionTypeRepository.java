package ru.sberbank.transactionmanager.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.sberbank.transactionmanager.domain.transaction.TransactionType;

@Repository
public interface TransactionTypeRepository extends CrudRepository<TransactionType, Long> {

    TransactionType findByCode(String code);

}
