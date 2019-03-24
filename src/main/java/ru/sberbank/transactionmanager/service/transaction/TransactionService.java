package ru.sberbank.transactionmanager.service.transaction;

import ru.sberbank.transactionmanager.rest.dto.transaction.TransactionDTO;

public interface TransactionService {

    /**
     * Получение информации о транзакции
     * @param transactionId идентификатор транзации
     * @return {@link TransactionDTO} данные транзакции
     */
    TransactionDTO getTransaction(Long transactionId);

}
