package ru.sberbank.transactionmanager.rest.controller.user;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.sberbank.transactionmanager.rest.annotation.BaseRestController;
import ru.sberbank.transactionmanager.rest.dto.user.UserInfoDTO;
import ru.sberbank.transactionmanager.service.user.UserInfoService;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.http.ResponseEntity.noContent;
import static org.springframework.http.ResponseEntity.ok;

@BaseRestController
@AllArgsConstructor
@Api(value = "User", description = "Оперции управления пользователями системы")
public class UserController {

    private static final String ID = "id";
    private static final String ID_ROUTE = "/{id}";
    private static final String BASE_USER_ROUTE = "/user";
    private static final String GET_USERS_ROUTE = BASE_USER_ROUTE + "/list";
    private static final String GET_USER_ROUTE = BASE_USER_ROUTE + ID_ROUTE;
    private static final String CREATE_ROUTE = BASE_USER_ROUTE + "/create";
    private static final String UPDATE_ROUTE = BASE_USER_ROUTE + "/update";
    private static final String DELETE_ROUTE = BASE_USER_ROUTE + "/delete" + ID_ROUTE;

    private UserInfoService userInfoService;

    /**
     * Получение перечня всех пользователей постранично
     *
     * @param pageable параметр паджинации
     * @return {@link Page<UserInfoDTO>}
     */
    @ApiOperation(value = "Получение перечня всех пользователей системы постранично", response = Page.class)
    @GetMapping(
            path = GET_USERS_ROUTE,
            produces = APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Page<UserInfoDTO>> getUsers(Pageable pageable) {
        return ok(userInfoService.getUsers(pageable));
    }

    /**
     * Создание пользователя
     *
     * @param userInfoDTO данные пользователя
     * @return {@link ResponseEntity<UserInfoDTO>}
     */
    @ApiOperation(value = "Создание пользователя системы", response = UserInfoDTO.class)
    @PutMapping(
            path = CREATE_ROUTE,
            produces = APPLICATION_JSON_VALUE
    )
    public ResponseEntity<UserInfoDTO> createUser(@RequestBody UserInfoDTO userInfoDTO) {
        return ok(userInfoService.createUser(userInfoDTO));
    }

    /**
     * Получение информации о пользователе
     *
     * @param userId идентификатор пользователя
     * @return {@link UserInfoDTO}
     */
    @ApiOperation(value = "Получение информации о пользователе системы", response = UserInfoDTO.class)
    @GetMapping(
            path = GET_USER_ROUTE,
            produces = APPLICATION_JSON_VALUE
    )
    public ResponseEntity<UserInfoDTO> getUser(@PathVariable(ID) Long userId) {
        return ok(userInfoService.getUser(userId));
    }

    /**
     * Редактирование информации о пользователе
     */
    @ApiOperation(value = "Редактирование информации о пользователе системы", response = UserInfoDTO.class)
    @PostMapping(
            path = UPDATE_ROUTE,
            produces = APPLICATION_JSON_VALUE
    )
    public ResponseEntity<UserInfoDTO> updateUser(@RequestBody UserInfoDTO userInfoDTO) {
        return ok(userInfoService.updateUser(userInfoDTO));
    }

    /**
     * Удаление пользователя
     *
     * @param userId идентификатор пользователя
     * @return {@link Void}
     */
    @ApiOperation(value = "Удаление пользователя системы")
    @DeleteMapping(path = DELETE_ROUTE)
    public ResponseEntity<UserInfoDTO> deleteUser(@PathVariable(ID) Long userId) {
        userInfoService.deleteUser(userId);
        return noContent().build();
    }

}
