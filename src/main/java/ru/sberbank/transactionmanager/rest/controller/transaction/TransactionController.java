package ru.sberbank.transactionmanager.rest.controller.transaction;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import ru.sberbank.transactionmanager.rest.annotation.BaseRestController;
import ru.sberbank.transactionmanager.rest.dto.transaction.TransactionDTO;
import ru.sberbank.transactionmanager.service.transaction.TransactionService;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.http.ResponseEntity.ok;

@BaseRestController
@AllArgsConstructor
@Api(value = "Transaction", description = "Оперции управления транзакциями пользователей")
public class TransactionController {

    private static final String BASE_TRANSACTION_ROUTE = "/transaction";
    private static final String ID_ROUTE = "/{id}";
    private static final String GET_ROUTE = BASE_TRANSACTION_ROUTE + ID_ROUTE;
    private static final String RECHARGE_ROUTE = BASE_TRANSACTION_ROUTE + "/recharge";
    private static final String WITHDRAWAL_ROUTE = BASE_TRANSACTION_ROUTE + "/withdrawal";
    private static final String REMITTANCE_ROUTE = BASE_TRANSACTION_ROUTE + "/remittance";

    private TransactionService transactionService;

    /**
     * Получение информации о транзакции
     * @param id идентификатор транзации
     * @return {@link TransactionDTO} данные транзакции
     */
    @ApiOperation(value = "Получение информации о транзакции", response = TransactionDTO.class)
    @GetMapping(
            path = GET_ROUTE,
            produces = APPLICATION_JSON_VALUE
    )
    public ResponseEntity<TransactionDTO> getUser(@PathVariable Long id) {
        return ok(transactionService.getTransaction(id));
    }



}
