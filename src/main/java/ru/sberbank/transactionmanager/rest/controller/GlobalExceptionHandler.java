package ru.sberbank.transactionmanager.rest.controller;


import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.util.WebUtils;
import ru.sberbank.transactionmanager.common.error.Error;
import ru.sberbank.transactionmanager.common.error.TransactionManagerException;
import ru.sberbank.transactionmanager.rest.dto.error.ErrorDTO;

import java.sql.SQLException;

/**
 * Глобальный обработчик исключений
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    private static final String SQL_ERROR_MESSAGE = "Ошибка при выполнении SQL";
    private static final String UNKNOWN_ERROR_MESSAGE = "Неизвестная ошибка сервера";

    @ExceptionHandler({ TransactionManagerException.class, SQLException.class })
    public final ResponseEntity<ErrorDTO> handleException(Exception exception, WebRequest request) {
        if (exception instanceof TransactionManagerException) {
            return handleTransactionManagerException((TransactionManagerException) exception, request);
        } else if (exception instanceof SQLException) {
            return handleSQLException((SQLException) exception, request);
        }
        return handleUnknownException(exception, request);
    }

    private ResponseEntity<ErrorDTO> handleTransactionManagerException(TransactionManagerException exception, WebRequest request) {
        ErrorDTO errorDTO = ErrorDTO.builder()
                .code(exception.getInternalError().getCode())
                .message(exception.getMessage())
                .build();
        return handleExceptionInternal(
                exception,
                errorDTO,
                new HttpHeaders(),
                HttpStatus.INTERNAL_SERVER_ERROR,
                request
        );
    }

    private ResponseEntity<ErrorDTO> handleSQLException(SQLException exception, WebRequest request) {
        ErrorDTO errorDTO = ErrorDTO.builder()
                .code(Error.SQL.getCode())
                .message(SQL_ERROR_MESSAGE)
                .build();
        return handleExceptionInternal(
                exception,
                errorDTO,
                new HttpHeaders(),
                HttpStatus.INTERNAL_SERVER_ERROR,
                request
        );
    }

    private ResponseEntity<ErrorDTO> handleUnknownException(Exception exception, WebRequest request) {
        ErrorDTO errorDTO = ErrorDTO.builder()
                .code(Error.UNKNOWN.getCode())
                .message(UNKNOWN_ERROR_MESSAGE)
                .build();
        return handleExceptionInternal(
                exception,
                errorDTO,
                new HttpHeaders(),
                HttpStatus.INTERNAL_SERVER_ERROR,
                request
        );
    }

    private ResponseEntity<ErrorDTO> handleExceptionInternal(
            Exception exception,
            ErrorDTO body,
            HttpHeaders headers,
            HttpStatus status,
            WebRequest request
    ) {
        if (HttpStatus.INTERNAL_SERVER_ERROR.equals(status)) {
            request.setAttribute(WebUtils.ERROR_EXCEPTION_ATTRIBUTE, exception, WebRequest.SCOPE_REQUEST);
        }
        return new ResponseEntity<>(body, headers, status);
    }

}

