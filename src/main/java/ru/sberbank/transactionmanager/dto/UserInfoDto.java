package ru.sberbank.transactionmanager.dto;

import lombok.*;

import java.time.LocalDate;

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
