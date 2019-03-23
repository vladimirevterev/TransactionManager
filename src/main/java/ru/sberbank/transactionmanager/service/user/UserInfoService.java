package ru.sberbank.transactionmanager.service.user;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ru.sberbank.transactionmanager.rest.dto.user.UserInfoDTO;

public interface UserInfoService {

    /**
     * Получение перечня всех пользователей постранично
     *
     * @param pageable параметр паджинации
     * @return {@link Page<UserInfoDTO>}
     */
    Page<UserInfoDTO> getUsers(Pageable pageable);

    /**
     * Создание пользователя
     *
     * @param userInfoDTO данные пользователя
     * @return {@link UserInfoDTO}
     */
    UserInfoDTO createUser(UserInfoDTO userInfoDTO);

    /**
     * Получение информации о пользователе
     *
     * @param userId идентификатор пользователя
     * @return {@link UserInfoDTO} информация о пользователе
     */
    UserInfoDTO getUser(Long userId);

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
