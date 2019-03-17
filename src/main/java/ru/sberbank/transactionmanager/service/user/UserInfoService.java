package ru.sberbank.transactionmanager.service.user;

import ru.sberbank.transactionmanager.domain.entity.user.UserInfo;
import ru.sberbank.transactionmanager.rest.dto.user.UserInfoDTO;

public interface UserInfoService {

    /**
     * Получение информации о пользователе с переданным идентификатором
     *
     * @param userId идентификатор пользователя
     * @return {@link UserInfoDTO} информация о пользователе
     */
    UserInfoDTO getUserInfo(Long userId);

    /**
     * Создание пользователя системы
     *
     * @param userInfoDTO данные пользователя
     * @return {@link UserInfoDTO}
     */
    UserInfoDTO createUserInfo(UserInfoDTO userInfoDTO);

}
