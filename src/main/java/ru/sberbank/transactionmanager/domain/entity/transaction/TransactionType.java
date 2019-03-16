package ru.sberbank.transactionmanager.domain.entity.transaction;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import ru.sberbank.transactionmanager.domain.entity.Identified;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Сущность, описывающая таблицу "Справочник типов тразакций"
 */
@Entity
@Table(name = "TRANSACTION_TYPE")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TransactionType extends Identified {

    /**
     * Код типа тразакции
     */
    @Column(name = "CODE", nullable = false, unique = true, length = 3)
    public String code;

    /**
     * Наименование типа транзации
     */
    @Column(name = "NAME", nullable = false, length = 64)
    public String name;

    /**
     * Описание типа транзакции
     */
    @Column(name = "DESCRIPTION")
    public String description;

}
