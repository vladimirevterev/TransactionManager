package ru.sberbank.transactionmanager.rest.dto.account;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import ru.sberbank.transactionmanager.rest.dto.AuditableDTO;
import ru.sberbank.transactionmanager.rest.dto.user.UserInfoDTO;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class AccountDTO extends AuditableDTO<Long> {

    /**
     * Номер счета
     */
    @ApiModelProperty(notes = "Номер счета")
    public String number;

    /**
     * Остаток средств по счету
     */
    @ApiModelProperty(notes = "Остаток средств по счету")
    public Double balance;

    /**
     * Идентификатор пользователя-владелеца счета
     */
    @ApiModelProperty(notes = "Идентификатор пользователя-владелеца счета")
    Long userId;

    /**
     * Признак, указывающий на то, что данный счет является главным для пользователя
     */
    @ApiModelProperty(notes = "Признак, указывающий на то, что данный счет является главным для пользователя")
    public Boolean isPrimary;

}
