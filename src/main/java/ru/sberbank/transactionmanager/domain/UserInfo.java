package ru.sberbank.transactionmanager.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "USER_INFO")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserInfo {

    /**
     * Идентификатор пользователя
     */
    @Id
    @GeneratedValue
    @Column(name = "Id", nullable = false)
    public Long id;

    /**
     * Имя
     */
    @Column(name = "FIRST_NAME", length = 64, nullable = false)
    public String firstName;

    /**
     * Фамилия
     */
    @Column(name = "LAST_NAME", length = 64, nullable = false)
    public String lastName;

    /**
     * Отчество
     */
    @Column(name = "MIDDLE_NAME", length = 64)
    public String middleName;

    /**
     * Дата рождения
     */
    @Column(name = "BIRTH_DATE")
    public LocalDate birthDate;

}
