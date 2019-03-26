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
     * Идентификатор пользователя-получателя средств
     */
    @ApiModelProperty(notes = "Идентификатор пользователя-получателя средств")
    Long recipientId;

    /**
     * Идентификатор счета пользователя-получателя средств
     */
    @ApiModelProperty(notes = "Идентификатор счета пользователя-получателя средств")
    Long accountId;

    /**
     * Номер счета пользователя-получателя средств
     */
    @ApiModelProperty(notes = "Номер счета пользователя-получателя средств")
    String accountNumber;

    /**
     * Сумма перевода
     */
    @ApiModelProperty(notes = "Сумма перевода")
    Double amount;

}
