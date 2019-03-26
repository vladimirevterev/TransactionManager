package ru.sberbank.transactionmanager.rest.dto.operation;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Класс, описывающий набор возможных передаваемых данных
 * клиентом при пополнении средств
 */
@Data
public class ReplenishmentDTO {

    /**
     * Идентификатор счета зачисления средств
     */
    @ApiModelProperty(notes = "Идентификатор счета зачисления средств")
    Long accountId;

    /**
     * Номер счета зачисления средств
     */
    @ApiModelProperty(notes = "Номер счета зачисления средств")
    String accountNumber;

    /**
     * Сумма пополнения
     */
    @ApiModelProperty(notes = "Сумма пополнения")
    Double amount;

}
