package ru.sberbank.transactionmanager.rest.controller.transaction;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import ru.sberbank.transactionmanager.common.error.TransactionManagerException;
import ru.sberbank.transactionmanager.rest.annotation.BaseRestController;
import ru.sberbank.transactionmanager.rest.dto.operation.RemittanceDTO;
import ru.sberbank.transactionmanager.rest.dto.operation.ReplenishmentDTO;
import ru.sberbank.transactionmanager.rest.dto.operation.WithdrawalDTO;
import ru.sberbank.transactionmanager.rest.dto.transaction.TransactionDTO;
import ru.sberbank.transactionmanager.service.transaction.TransactionService;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.http.ResponseEntity.ok;

@BaseRestController
@AllArgsConstructor
@Api(value = "Transaction", description = "Оперции управления транзакциями пользователей")
public class TransactionController {

    private static final String BASE_TRANSACTION_ROUTE = "/transaction";
    private static final String ID_ROUTE = "/{id}";
    private static final String GET_ROUTE = BASE_TRANSACTION_ROUTE + ID_ROUTE;
    private static final String USER_LIST_ROUTE = BASE_TRANSACTION_ROUTE + "/list";
    private static final String REPLENISHMENT_ROUTE = BASE_TRANSACTION_ROUTE + "/replenishment";
    private static final String WITHDRAWAL_ROUTE = BASE_TRANSACTION_ROUTE + "/withdrawal";
    private static final String REMITTANCE_ROUTE = BASE_TRANSACTION_ROUTE + "/remittance";

    private TransactionService transactionService;

    /**
     * Получение информации о транзакции текущего пользователя
     *
     * @param id идентификатор транзации
     * @return {@link TransactionDTO} данные транзакции
     */
    @ApiOperation(value = "Получение информации о транзакции текущего пользователя", response = TransactionDTO.class)
    @GetMapping(
            path = GET_ROUTE,
            produces = APPLICATION_JSON_VALUE
    )
    public ResponseEntity<TransactionDTO> getUser(@PathVariable Long id) throws TransactionManagerException {
        return ok(transactionService.getTransaction(id));
    }

    /**
     * Получение списка транзакций текущего пользователя
     *
     * @param pageable параметры паджинации
     * @return {@link Page<TransactionDTO>} страница транзакций пользователя
     */
    @ApiOperation(value = "Получение списка транзакций текущего пользователя", response = Page.class)
    @GetMapping(
            path = USER_LIST_ROUTE,
            produces = APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Page<TransactionDTO>> getUserTransactions(Pageable pageable) throws TransactionManagerException {
        return ok(transactionService.getTransactions(pageable));
    }

    /**
     * Перевод денежных средств пользователю
     *
     * @param remittanceDTO данные о переводе
     */
    @ApiOperation(value = "Перевод денежных средств пользователю")
    @PostMapping(
            path = REMITTANCE_ROUTE
    )
    public ResponseEntity<Void> transferFunds(@RequestBody RemittanceDTO remittanceDTO) throws TransactionManagerException {
        transactionService.transferFunds(remittanceDTO);
        return ok().build();
    }

    /**
     * Пополнение денежных средств
     *
     * @param replenishmentDTO данные по операции пополнения
     */
    @ApiOperation(value = "Пополнение денежных средств")
    @PostMapping(
            path = REPLENISHMENT_ROUTE
    )
    public ResponseEntity<Void> replenishFunds(@RequestBody ReplenishmentDTO replenishmentDTO) throws TransactionManagerException {
        transactionService.replenishFunds(replenishmentDTO);
        return ok().build();
    }

    /**
     * Снятие денежных средств
     *
     * @param withdrawalDTO данные по операции снятия
     */
    @ApiOperation(value = "Снятие денежных средств")
    @PostMapping(
            path = WITHDRAWAL_ROUTE
    )
    public ResponseEntity<Void> withdrawalFunds(@RequestBody WithdrawalDTO withdrawalDTO) throws TransactionManagerException {
        transactionService.withdrawalFunds(withdrawalDTO);
        return ok().build();
    }

}
