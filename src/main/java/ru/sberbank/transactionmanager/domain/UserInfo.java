package ru.sberbank.transactionmanager.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "USER_INFO")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserInfo implements Serializable {

    /**
     * Идентификатор пользователя
     */
    @Id
    @GeneratedValue
    @Column(name = "ID", nullable = false)
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

    /**
     * Список {@link Account} счетов пользователя
     */
    @OneToMany(mappedBy = "userInfo", cascade = CascadeType.ALL, orphanRemoval = true)
    List<Account> accounts;

    /**
     * Дата создания пользователя в системе
     */
    @Temporal(TemporalType.TIME)
    @Generated(GenerationTime.INSERT)
    @Column(name="CREATED_TIME", insertable=false)
    public Date createdTime;

    /**
     * Дата обновления пользователя в системе
     */
    @Temporal(TemporalType.TIME)
    @Generated(GenerationTime.ALWAYS)
    @Column(name="MODIFIED_TIME", insertable=false, updatable=false)
    public Date modifiedTime;

}
