package ru.sberbank.transactionmanager.utils;

import ru.sberbank.transactionmanager.common.error.Error;
import ru.sberbank.transactionmanager.common.error.TransactionManagerException;

/**
 * Вспомогательный класс для работы с внутренними ошибками приложения
 */
public class ErrorHelper {

    /**
     * Метод создания ошибки
     *
     * @param message текст ошибки
     * @param error параметры ошибки
     */
    public static void makeError(String message, Error error) throws TransactionManagerException {
        throw new TransactionManagerException(message, error);
    }

    /**
     * Метод проверки условия. Если условие выполнено, создается ошибка
     *
     * @param condition условие
     * @param message текст ошибки
     * @param error данные ошибки
     */
    public static void check(Boolean condition, String message, Error error) throws TransactionManagerException {
        if (condition) {
            makeError(message, error);
        }
    }

}
