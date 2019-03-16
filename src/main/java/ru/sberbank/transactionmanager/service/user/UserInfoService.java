package ru.sberbank.transactionmanager.service.user;

import ru.sberbank.transactionmanager.domain.entity.user.UserInfo;
import ru.sberbank.transactionmanager.rest.dto.user.UserInfoDTO;

public interface UserInfoService {

    /**
     * Получение информации о пользователе с переданным идентификатором
     *
     * @param userId идентификатор пользователя
     * @return {@link UserInfo} информация о пользователе
     */
    UserInfoDTO getUserInfo(Long userId);

}
