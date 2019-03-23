package ru.sberbank.transactionmanager.common.dictionary;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum TransactionTypeDictionary {

    RECHARGE    ("01", "Пополнение баланса"),
    WITHDRAWAL  ("02", "Выдача наличных"),
    REMITTANCE  ("03", "Денежный перевод");

    private final String code;
    private final String description;

}
