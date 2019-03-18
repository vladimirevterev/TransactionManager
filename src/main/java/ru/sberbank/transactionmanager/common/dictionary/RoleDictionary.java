package ru.sberbank.transactionmanager.common.dictionary;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum RoleDictionary implements Dictionary {

    ANONYMOUS   ("01", "Неавторизованный пользователь"),
    USER        ("02", "Пользователь системы"),
    ADMIN       ("03", "Пользователь системы с правами администратора"),
    SYSTEM      ("04", "Системный пользователь");

    public static final String ROLE_PREFIX = "ROLE_";

    private final String code;
    private final String description;

}
