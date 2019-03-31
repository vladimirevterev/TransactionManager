package ru.sberbank.transactionmanager.rest.dto.operation;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Класс, описывающий набор возможных передаваемых данных
 * клиентом при переводе средств на счет пользователя
 */
@Data
public class RemittanceDTO {

    /**
     * Идентификатор счета списания средств
     */
    @ApiModelProperty(notes = "Идентификатор счета списания средств")
    Long sourceAccountId;

    /**
     * Идентификатор пользователя-получателя средств
     */
    @ApiModelProperty(notes = "Идентификатор пользователя-получателя средств")
    Long recipientId;

    /**
     * Идентификатор счета пользователя-получателя средств
     */
    @ApiModelProperty(notes = "Идентификатор счета пользователя-получателя средств")
    Long recipientAccountId;

    /**
     * Сумма перевода
     */
    @ApiModelProperty(notes = "Сумма перевода")
    Double amount;

}
