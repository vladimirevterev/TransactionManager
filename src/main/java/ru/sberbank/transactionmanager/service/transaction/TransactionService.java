package ru.sberbank.transactionmanager.service.transaction;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ru.sberbank.transactionmanager.common.error.TransactionManagerException;
import ru.sberbank.transactionmanager.rest.dto.operation.RemittanceDTO;
import ru.sberbank.transactionmanager.rest.dto.operation.ReplenishmentDTO;
import ru.sberbank.transactionmanager.rest.dto.operation.WithdrawalDTO;
import ru.sberbank.transactionmanager.rest.dto.transaction.TransactionDTO;

public interface TransactionService {

    /**
     * Получение информации о транзакции текущего пользователя
     *
     * @param transactionId идентификатор транзации
     * @return {@link TransactionDTO} данные транзакции
     */
    TransactionDTO getTransaction(Long transactionId) throws TransactionManagerException;

    /**
     * Получение списка транзакций текущего пользователя
     *
     * @param pageable параметры паджинации
     * @return {@link Page<TransactionDTO>} страница транзакций пользователя
     */
    Page<TransactionDTO> getTransactions(Pageable pageable) throws TransactionManagerException;

    /**
     * Перевод денежных средств пользователю
     *
     * @param remittanceDTO данные по переводу средств
     */
    void transferFunds(RemittanceDTO remittanceDTO) throws TransactionManagerException;

    /**
     * Пополнение денежных средств
     *
     * @param replenishmentDTO данные по пополнению средств
     */
    void replenishFunds(ReplenishmentDTO replenishmentDTO) throws TransactionManagerException;

    /**
     * Снятие денежных средств
     *
     * @param withdrawalDTO данные по снятию средств
     */
    void withdrawalFunds(WithdrawalDTO withdrawalDTO) throws TransactionManagerException;

}
