package ru.sberbank.transactionmanager.dto.user;

import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserInfoDto {

    /**
     * Идентификатор пользователя
     */
    public Long id;

    /**
     * Дата создания пользователя
     */
    public LocalDateTime createdTime;

    /**
     * Дата редактирования пользователя
     */
    public LocalDateTime modifiedTime;

    /**
     * Имя
     */
    public String firstName;

    /**
     * Фамилия
     */
    public String lastName;

    /**
     * Отчество
     */
    public String middleName;

    /**
     * Дата рождения
     */
    public LocalDate birthDate;

}
