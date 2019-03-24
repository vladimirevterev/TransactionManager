package ru.sberbank.transactionmanager.rest.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public abstract class IdentifiedDTO {

    /**
     * Идентификатор записи
     */
    @ApiModelProperty(notes = "Идентификатор записи")
    public Long id;

}
