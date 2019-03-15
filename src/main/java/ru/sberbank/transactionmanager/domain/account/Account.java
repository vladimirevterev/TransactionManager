package ru.sberbank.transactionmanager.domain.account;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import ru.sberbank.transactionmanager.annotation.AccountNumberGeneration;
import ru.sberbank.transactionmanager.domain.Auditable;
import ru.sberbank.transactionmanager.domain.userinfo.UserInfo;

import javax.persistence.*;

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
    @Column(name = "BALANCE", nullable = false)
    public Double balance;

    /**
     * Пользователь-владелец счета
     */
    @ManyToOne
    @JoinColumn(name = "USER_INFO_ID", foreignKey = @ForeignKey(name = "USER_INFO_ID_FK"))
    UserInfo userInfo;

    /**
     * Признак, указывающий на то, что данный счет является главным для пользователя
     */
    @Column(name = "IS_PRIMARY", nullable = false)
    public Boolean isPrimary;

}
