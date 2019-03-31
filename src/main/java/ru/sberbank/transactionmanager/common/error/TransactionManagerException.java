package ru.sberbank.transactionmanager.common.error;

import lombok.Getter;

/**
 * Класс исключений, который вызывается в случае ошибок логики приложения
 */
@Getter
public class TransactionManagerException extends Exception {

    /**
     * Данные ошибки
     */
    Error internalError;

    public TransactionManagerException(String message, Error error) {
        super(message);
        this.internalError = error;
    }

}
