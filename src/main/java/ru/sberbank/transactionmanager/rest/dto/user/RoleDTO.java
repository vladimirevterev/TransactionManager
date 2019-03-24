package ru.sberbank.transactionmanager.rest.dto.user;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import ru.sberbank.transactionmanager.rest.dto.IdentifiedDTO;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class RoleDTO extends IdentifiedDTO {

    /**
     * Код роли
     */
    @ApiModelProperty(notes = "Код роли")
    public String code;

    /**
     * Наименование роли
     */
    @ApiModelProperty(notes = "Наименование роли")
    public String name;

    /**
     * Описание роли
     */
    @ApiModelProperty(notes = "Описание роли")
    public String description;

}
