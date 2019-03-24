package ru.sberbank.transactionmanager.rest.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public abstract class AuditableDTO<U> extends IdentifiedDTO {

    /**
     * Пользователь-создатель записи
     */
    @ApiModelProperty(notes = "Пользователь-создатель записи")
    public U createdBy;

    /**
     * Дата создания записи в системе
     */
    @ApiModelProperty(notes = "Дата создания записи в системе")
    public LocalDateTime creationDate;

    /**
     * Пользователь, который модифицировал запись
     */
    @ApiModelProperty(notes = "Пользователь, который модифицировал запись")
    public U lastModifiedBy;

    /**
     * Дата обновления записи в системе
     */
    @ApiModelProperty(notes = "Дата обновления записи в системе")
    public LocalDateTime lastModifiedDate;

}
