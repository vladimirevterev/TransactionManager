package ru.sberbank.transactionmanager.rest.dto.transaction;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import ru.sberbank.transactionmanager.rest.dto.AuditableDTO;
import ru.sberbank.transactionmanager.rest.dto.account.AccountDTO;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class TransactionDTO extends AuditableDTO<Long> {

    /**
     * UUID транзакции
     */
    @ApiModelProperty(notes = "UUID транзакции")
    public String uuid;

    /**
     * Тип тразакции
     */
    @ApiModelProperty(notes = "Тип тразакции")
    public TransactionTypeDTO transactionType;

    /**
     * Счет списания средств
     */
    @ApiModelProperty(notes = "Счет списания средств")
    public AccountDTO sourceAccount;

    /**
     * Счет зачисления средств
     */
    @ApiModelProperty(notes = "Счет зачисления средств")
    public AccountDTO destinationAccount;

    /**
     * Сумма перевода
     */
    @ApiModelProperty(notes = "Сумма перевода")
    public Double amount;

}
