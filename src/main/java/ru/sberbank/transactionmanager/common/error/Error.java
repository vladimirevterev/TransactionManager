package ru.sberbank.transactionmanager.common.error;

import lombok.Getter;
import org.springframework.http.HttpStatus;

/**
 * Перечисление возможных ошибок
 */
@Getter
public enum Error {

    /**
     * Неизвестная ошибка сервера
     */
    UNKNOWN("TM-UNKNOWN"),
    /**
     * Ошибка при выполнении SQL
     */
    SQL("TM-SQL"),
    /**
     * Пользователь не найден
     */
    USER_NOT_FOUND("TM-001", HttpStatus.NOT_FOUND),
    /**
     * Текущий пользователь не задан
     */
    CURRENT_USER_NOT_SPECIFIED("TM-002", HttpStatus.BAD_REQUEST),
    /**
     * С пользователем не связано ни одного счета
     */
    USER_HAS_NO_ACCOUNTS("ТМ-003", HttpStatus.BAD_REQUEST),
    /**
     * Недостаточно средств на счете
     */
    INSUFFICIENT_FUNDS("TM-004", HttpStatus.BAD_REQUEST),
    /**
     * Счет не найден
     */
    ACCOUNT_NOT_FOUND("TM-005", HttpStatus.NOT_FOUND),
    /**
     * Пользователь и счет не связаны
     */
    USER_IS_NOT_ASSOCIATED_WITH_ACCOUNT("TM-006", HttpStatus.BAD_REQUEST),
    /**
     * Сумма для операции перевода/пополнения/снятия средств не задана
     */
    TRANSACTION_AMOUNT_NOT_SPECIFIED("TM-007", HttpStatus.BAD_REQUEST),
    /**
     * Сумма для операции перевода/пополнения/снятия средств не положительная
     */
    TRANSACTION_AMOUNT_NOT_POSITIVE("TM-008", HttpStatus.BAD_REQUEST),
    /**
     * Получатель средств не указан
     */
    RECIPIENT_NOT_SPECIFIED("TM-009", HttpStatus.BAD_REQUEST),
    /**
     * Информация о транзакции не найдена
     */
    TRANSACTION_NOT_FOUND("TM-010", HttpStatus.NOT_FOUND),
    /**
     * Пользователь и траназкция не связаны
     */
    USER_IS_NOT_ASSOCIATED_WITH_TRANSACTION("TM-011", HttpStatus.BAD_REQUEST),
    /**
     * Удаление пользователя запрещено
     */
    REMOVING_USER_IS_FORBIDDEN("TM-012", HttpStatus.BAD_REQUEST)
    ;


    private final String code;
    private final HttpStatus status;

    Error(String code) {
        this.code = code;
        this.status = HttpStatus.INTERNAL_SERVER_ERROR;
    }

    Error(String code, HttpStatus status) {
        this.code = code;
        this.status = status;
    }

}
