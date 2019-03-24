package ru.sberbank.transactionmanager.rest.dto.transaction;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import ru.sberbank.transactionmanager.rest.dto.IdentifiedDTO;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class TransactionTypeDTO extends IdentifiedDTO {

    /**
     * Код типа транзакции
     */
    @ApiModelProperty(notes = "Код типа транзакции")
    public String code;

    /**
     * Наименование типа транзакции
     */
    @ApiModelProperty(notes = "Наименование типа транзакции")
    public String name;

    /**
     * Описание типа транзакции
     */
    @ApiModelProperty(notes = "Описание типа транзакции")
    public String description;

}
