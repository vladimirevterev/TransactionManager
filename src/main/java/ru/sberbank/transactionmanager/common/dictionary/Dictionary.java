package ru.sberbank.transactionmanager.common.dictionary;

/**
 * Описание контракта словаря
 */
public interface Dictionary {

    /**
     * Получение кода значения словаря
     *
     * @return {@link String} код
     */
    String getCode();

    /**
     * Получение описания значения словаря
     *
     * @return {@link String} описание
     */
    String getDescription();

}
