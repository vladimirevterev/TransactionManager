package ru.sberbank.transactionmanager.service.userinfo;

import ru.sberbank.transactionmanager.domain.entity.user.UserInfo;
import ru.sberbank.transactionmanager.dto.user.UserInfoDto;

public interface UserInfoService {

    /**
     * Получение информации о пользователе с переданным идентификатором
     *
     * @param userId идентификатор пользователя
     * @return {@link UserInfo} информация о пользователе
     */
    UserInfoDto getUserInfo(Long userId);

}
