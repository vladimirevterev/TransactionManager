package ru.sberbank.transactionmanager.domain.entity.user;

import lombok.*;
import ru.sberbank.transactionmanager.domain.entity.Auditable;
import ru.sberbank.transactionmanager.domain.entity.account.Account;

import javax.persistence.*;
import javax.validation.constraints.PastOrPresent;
import java.time.LocalDate;
import java.util.List;

/**
 * Сущность, описывающая таблицу "Пользователь"
 */
@Entity
@Table(name = "USER_INFO")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserInfo extends Auditable<Long> {

    /**
     * Логин пользователя
     */
    @Column(name = "LOGIN", length = 64, nullable = false)
    public String login;

    /**
     * Пароль пользователя в зашифрованном виде
     */
    @Column(name = "ENCRYPTED_PASSWORD")
    public String encryptedPassword;

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
    @PastOrPresent
    public LocalDate birthDate;

    /**
     * Список {@link Account} счетов пользователя
     */
    @OneToMany(mappedBy = "userInfo", cascade = CascadeType.ALL, orphanRemoval = true)
    List<Account> accounts;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    @JoinTable(
            name = "USER_ROLE",
            joinColumns = @JoinColumn(name = "USER_INFO_ID"),
            inverseJoinColumns = @JoinColumn(name = "ROLE_ID"))
    List<Role> roles;

}
