package ru.sberbank.transactionmanager.domain.entity.transaction;

import lombok.*;
import ru.sberbank.transactionmanager.domain.generator.annotation.UuidGeneration;
import ru.sberbank.transactionmanager.domain.entity.Auditable;
import ru.sberbank.transactionmanager.domain.entity.account.Account;

import javax.persistence.*;
import javax.validation.constraints.PositiveOrZero;

/**
 * Сущность, описывающая таблицу "История транзакций"
 */
@Entity
@Table(name = "TRANSACTION")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Transaction extends Auditable<Long> {

    /**
     * UUID транзакции
     */
    @UuidGeneration
    @Column(
            name = "UUID",
            unique = true,
            nullable = false
    )
    public String uuid;

    /**
     * Тип тразакции
     */
    @ManyToOne
    @JoinColumn(
            name = "TRANSACTION_TYPE_ID",
            foreignKey = @ForeignKey(name = "TRANSACTION_TYPE_TRANSACTION_HISTORY_FK"),
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
