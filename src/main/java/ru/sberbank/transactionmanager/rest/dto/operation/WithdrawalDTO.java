package ru.sberbank.transactionmanager.rest.dto.operation;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Класс, описывающий набор возможных передаваемых данных
 * клиентом при снятии средств
 */
@Data
public class WithdrawalDTO {

    /**
     * Идентификатор счета списания средств
     */
    @ApiModelProperty(notes = "Идентификатор счета списания средств")
    Long accountId;

    /**
     * Номер счета списания средств
     */
    @ApiModelProperty(notes = "Номер счета списания средств")
    String accountNumber;

    /**
     * Сумма списания
     */
    @ApiModelProperty(notes = "Сумма списания")
    Double amount;

}
