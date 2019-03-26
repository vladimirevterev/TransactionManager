package ru.sberbank.transactionmanager.service.transaction;

import ru.sberbank.transactionmanager.rest.dto.operation.RemittanceDTO;
import ru.sberbank.transactionmanager.rest.dto.operation.ReplenishmentDTO;
import ru.sberbank.transactionmanager.rest.dto.operation.WithdrawalDTO;
import ru.sberbank.transactionmanager.rest.dto.transaction.TransactionDTO;

public interface TransactionService {

    /**
     * Получение информации о транзакции
     * @param transactionId идентификатор транзации
     * @return {@link TransactionDTO} данные транзакции
     */
    TransactionDTO getTransaction(Long transactionId);

    /**
     * Перевод денежных средств пользователю
     * @param remittanceDTO данные по переводу средств
     */
    void transferFunds(RemittanceDTO remittanceDTO);

    /**
     * Пополнение денежных средств
     * @param replenishmentDTO данные по пополнению средств
     */
    void replenishFunds(ReplenishmentDTO replenishmentDTO);

    /**
     * Снятие денежных средств
     * @param withdrawalDTO данные по снятию средств
     */
    void withdrawalFunds(WithdrawalDTO withdrawalDTO);

}
