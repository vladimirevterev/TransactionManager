package ru.sberbank.transactionmanager.service.user;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ru.sberbank.transactionmanager.common.error.TransactionManagerException;
import ru.sberbank.transactionmanager.rest.dto.account.AccountDTO;
import ru.sberbank.transactionmanager.rest.dto.user.UserInfoDTO;

public interface UserInfoService {

    /**
     * Получение информации о пользователе
     *
     * @param userId идентификатор пользователя
     * @return {@link UserInfoDTO} информация о пользователе
     */
    UserInfoDTO getUser(Long userId) throws TransactionManagerException;

    /**
     * Получение информации о текущем пользователе
     *
     * @return {@link UserInfoDTO} информация о текущем пользователе
     */
    UserInfoDTO getCurrentUser() throws TransactionManagerException;

    /**
     * Получение перечня всех пользователей постранично
     *
     * @param pageable параметр паджинации
     * @return {@link Page<UserInfoDTO>} страница с информацией о пользователях системы
     */
    Page<UserInfoDTO> getUsers(Pageable pageable);

    /**
     * Получение перечня счетов пользователя постранично
     *
     * @param userId идентификатор пользователя
     * @param pageable параметры паджинации
     * @return {@link Page<AccountDTO>} страница с информацией о счетах пользователя
     */
    Page<AccountDTO> getUserAccounts(Long userId, Pageable pageable) throws TransactionManagerException;

    /**
     * Получение перечня счетов текущего пользователя постранично
     *
     * @param pageable параметры паджинации
     * @return {@link Page<AccountDTO>} страница с информацией о счетах пользователя
     */
    Page<AccountDTO> getCurrenUserAccounts(Pageable pageable) throws TransactionManagerException;

    /**
     * Создание пользователя
     *
     * @param userInfoDTO данные пользователя
     * @return {@link UserInfoDTO}
     */
    UserInfoDTO createUser(UserInfoDTO userInfoDTO);

    /**
     * Редактирование информации о пользователе
     *
     * @param userInfoDTO обновленные данные пользователя
     * @return {@link UserInfoDTO}
     */
    UserInfoDTO updateUser(UserInfoDTO userInfoDTO);

    /**
     * Удаление пользователя
     *
     * @param userId идентификатор пользователя
     */
    void deleteUser(Long userId);

}
