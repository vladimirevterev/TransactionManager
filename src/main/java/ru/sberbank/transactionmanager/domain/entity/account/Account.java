package ru.sberbank.transactionmanager.domain.entity.account;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import ru.sberbank.transactionmanager.domain.generator.annotation.AccountNumberGeneration;
import ru.sberbank.transactionmanager.domain.entity.Auditable;
import ru.sberbank.transactionmanager.domain.entity.user.UserInfo;

import javax.persistence.*;
import javax.validation.constraints.PositiveOrZero;

/**
 * Сущность, описывающая таблицу "Счет"
 */
@Entity
@Table(name = "ACCOUNT")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Account extends Auditable<Long> {

    /**
     * Номер счета
     */
    @AccountNumberGeneration
    @Column(name = "NUMBER")
    public String number;

    /**
     * Остаток средств по счету
     */
    @Column(
            name = "BALANCE",
            nullable = false,
            columnDefinition = "Decimal(10,2) default '100.00'"
    )
    @PositiveOrZero
    public Double balance;

    /**
     * Пользователь-владелец счета
     */
    @ManyToOne
    @JoinColumn(
            name = "USER_INFO_ID",
            foreignKey = @ForeignKey(name = "USER_INFO_ID_FK"),
            nullable = false
    )
    UserInfo userInfo;

    /**
     * Признак, указывающий на то, что данный счет является главным для пользователя
     */
    @Column(name = "IS_PRIMARY", nullable = false)
    public Boolean isPrimary;

}
