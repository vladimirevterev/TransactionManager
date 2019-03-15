package ru.sberbank.transactionmanager.domain.userinfo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import ru.sberbank.transactionmanager.domain.Auditable;
import ru.sberbank.transactionmanager.domain.account.Account;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "USER_INFO")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserInfo extends Auditable<Long> {

    /**
     * Логин пользователя
     */
    @Column(name = "LOGIN", length = 64, nullable = false)
    public String login;

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

    /**
     * Список {@link Account} счетов пользователя
     */
    @OneToMany(mappedBy = "userInfo", cascade = CascadeType.ALL, orphanRemoval = true)
    List<Account> accounts;

}
