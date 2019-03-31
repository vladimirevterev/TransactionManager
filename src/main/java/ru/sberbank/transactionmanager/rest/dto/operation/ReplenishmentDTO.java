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
     * Сумма пополнения
     */
    @ApiModelProperty(notes = "Сумма пополнения")
    Double amount;

}
