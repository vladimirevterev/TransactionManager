package ru.sberbank.transactionmanager.rest.dto.error;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class ErrorDTO {

    /**
     * Описание ошибки
     */
    @ApiModelProperty(notes = "Описание ошибки")
    String message;

    /**
     * Код ошибки
     */
    @ApiModelProperty(notes = "Код ошибки")
    String code;

}
