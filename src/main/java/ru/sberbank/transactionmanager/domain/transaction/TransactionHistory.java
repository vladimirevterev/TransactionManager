package ru.sberbank.transactionmanager.domain.transaction;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import ru.sberbank.transactionmanager.annotation.UuidGeneration;
import ru.sberbank.transactionmanager.domain.Auditable;
import ru.sberbank.transactionmanager.domain.account.Account;

import javax.persistence.*;
import javax.validation.constraints.PositiveOrZero;

/**
 * Сущность, описывающая таблицу "История транзакций"
 */
@Entity
@Table(name = "TRANSACTION_HISTORY")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TransactionHistory extends Auditable<Long> {

    /**
     * Код транзакции
     */
    @UuidGeneration
    @Column(
            name = "CODE",
            unique = true,
            nullable = false
    )
    public String code;

    /**
     * Тип тразакции
     */
    @ManyToOne
    @JoinColumn(
            name = "TRANSACTION_TYPE_ID",
            foreignKey = @ForeignKey(name = "TRANSACTION_TYPE_ID_FK"),
            nullable = false
    )
    public TransactionType transactionType;

    /**
     * Счет списания средств
     */
    @ManyToOne
    @JoinColumn(
            name = "SOURCE_ACCOUNT_ID",
            foreignKey = @ForeignKey(name = "SOURCE_ACCOUNT_ID_FK"),
            nullable = false
    )
    public Account sourceAccount;

    /**
     * Счет зачисления средств
     */
    @ManyToOne
    @JoinColumn(
            name = "DESTINATION_ACCOUNT_ID",
            foreignKey = @ForeignKey(name = "DESTINATION_ACCOUNT_ID_FK"),
            nullable = false
    )
    public Account destinationAccount;

    /**
     * Сумма перевода
     */
    @PositiveOrZero
    @Column(name = "AMOUNT", nullable = false)
    public Double amount;

}
