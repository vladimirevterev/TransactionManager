package ru.sberbank.transactionmanager.rest.controller.user;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.sberbank.transactionmanager.rest.dto.user.UserInfoDTO;
import ru.sberbank.transactionmanager.service.user.UserInfoService;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.http.ResponseEntity.ok;

@RestController
@AllArgsConstructor
@RequestMapping("/user")
@Api(value = "user", description = "Оперции управления пользователями системы")
public class UserController {

    private static final String BASIC_ROUTE = "/";
    private static final String ID_ROUTE = "/{id}";


    @Autowired
    private UserInfoService userInfoService;

    /**
     * Получение информации о пользователе
     * @param id идентификатор пользователя
     * @return {@link ResponseEntity<UserInfoDTO>}
     */
    @ApiOperation(value = "Получение информации о пользователе", response = UserInfoDTO.class)
    @GetMapping(
            path = ID_ROUTE,
            produces = APPLICATION_JSON_VALUE
    )
    public ResponseEntity<UserInfoDTO> getUserInfo(@PathVariable Long id) {
        return ok(userInfoService.getUserInfo(id));
    }

    /**
     * Создание пользователя системы
     * @return {@link ResponseEntity<UserInfoDTO>}
     */
    @ApiOperation(value = "Создание пользователя системы", response = UserInfoDTO.class)
    @PutMapping(
            path = BASIC_ROUTE,
            produces = APPLICATION_JSON_VALUE
    )
    public ResponseEntity<UserInfoDTO> createUser(@RequestBody UserInfoDTO userInfoDTO) {
        return ok(userInfoService.createUserInfo(userInfoDTO));
    }

}
