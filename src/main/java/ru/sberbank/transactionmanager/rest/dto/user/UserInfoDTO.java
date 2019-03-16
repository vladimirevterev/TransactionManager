package ru.sberbank.transactionmanager.rest.dto.user;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserInfoDTO {

    /**
     * Идентификатор пользователя
     */
    @ApiModelProperty(notes = "Идентификатор пользователя")
    public Long id;

    /**
     * Дата создания пользователя
     */
    @ApiModelProperty(notes = "Дата создания пользователя")
    public LocalDateTime createdTime;

    /**
     * Дата редактирования пользователя
     */
    @ApiModelProperty(notes = "Дата редактирования пользователя")
    public LocalDateTime modifiedTime;

    /**
     * Имя пользователя
     */
    @ApiModelProperty(notes = "Имя пользователя")
    public String firstName;

    /**
     * Фамилия пользователя
     */
    @ApiModelProperty(notes = "Фамилия пользователя")
    public String lastName;

    /**
     * Отчество пользователя
     */
    @ApiModelProperty(notes = "Отчество пользователя")
    public String middleName;

    /**
     * Дата рождения пользователя
     */
    @ApiModelProperty(notes = "Дата рождения пользователя")
    public LocalDate birthDate;

}
