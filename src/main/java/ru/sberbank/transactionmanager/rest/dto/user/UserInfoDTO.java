package ru.sberbank.transactionmanager.rest.dto.user;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import ru.sberbank.transactionmanager.rest.dto.AuditableDTO;
import ru.sberbank.transactionmanager.rest.dto.account.AccountDTO;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserInfoDTO extends AuditableDTO<Long> {

    /**
     * Логин пользователя
     */
    @ApiModelProperty(notes = "Логин пользователя")
    public String login;

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

    /**
     * Список ролей пользователя
     */
    @ApiModelProperty(notes = "Список ролей пользователя")
    public List<RoleDTO> roles;

    /**
     * Список счетов пользователя
     */
    @ApiModelProperty(notes = "Список счетов пользователя")
    public List<AccountDTO> accounts;

}
