package ru.sberbank.transactionmanager.rest.controller.user;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.sberbank.transactionmanager.common.error.TransactionManagerException;
import ru.sberbank.transactionmanager.rest.annotation.BaseRestController;
import ru.sberbank.transactionmanager.rest.dto.account.AccountDTO;
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
    private static final String ID_PART_ROUTE = "/{id}";
    private static final String CURRENT_PART_ROUTE = "/current";
    private static final String ACCOUNTS_PART_ROUTE = "/accounts";
    private static final String BASE_USER_ROUTE = "/user";
    private static final String GET_ROUTE = BASE_USER_ROUTE + ID_PART_ROUTE;
    private static final String GET_ACCOUNTS_ROUTE = GET_ROUTE + ACCOUNTS_PART_ROUTE;
    private static final String GET_CURRENT_ROUTE = BASE_USER_ROUTE + CURRENT_PART_ROUTE;
    private static final String GET_CURRENT_ACCOUNTS_ROUTE = GET_CURRENT_ROUTE + ACCOUNTS_PART_ROUTE;
    private static final String GET_LIST_ROUTE = BASE_USER_ROUTE + "/list";
    private static final String CREATE_ROUTE = BASE_USER_ROUTE + "/create";
    private static final String UPDATE_ROUTE = BASE_USER_ROUTE + "/update";
    private static final String DELETE_ROUTE = BASE_USER_ROUTE + "/delete" + ID_PART_ROUTE;

    private UserInfoService userInfoService;

    /**
     * Получение информации о пользователе
     *
     * @param userId идентификатор пользователя
     * @return {@link UserInfoDTO} информация о пользователе
     */
    @ApiOperation(value = "Получение информации о пользователе системы", response = UserInfoDTO.class)
    @GetMapping(
            path = GET_ROUTE,
            produces = APPLICATION_JSON_VALUE
    )
    public ResponseEntity<UserInfoDTO> getUser(@PathVariable(ID) Long userId) throws TransactionManagerException {
        return ok(userInfoService.getUser(userId));
    }

    /**
     * Получение информации о текущем пользователе
     *
     * @return {@link UserInfoDTO} информация о текущем пользователе
     */
    @ApiOperation(value = "Получение информации о текущем пользователе системы")
    @GetMapping(
            path = GET_CURRENT_ROUTE,
            produces = APPLICATION_JSON_VALUE
    )
    public ResponseEntity<UserInfoDTO> getCurrentUser() throws TransactionManagerException {
        return ok(userInfoService.getCurrentUser());
    }

    /**
     * Получение перечня всех пользователей постранично
     *
     * @param pageable параметр паджинации
     * @return {@link Page<UserInfoDTO>} страница с информацией о пользователях системы
     */
    @ApiOperation(value = "Получение перечня всех пользователей системы постранично", response = Page.class)
    @GetMapping(
            path = GET_LIST_ROUTE,
            produces = APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Page<UserInfoDTO>> getUsers(Pageable pageable) {
        return ok(userInfoService.getUsers(pageable));
    }

    /**
     * Получение перечня счетов пользователя постранично
     *
     * @param userId идентификатор пользователя
     * @param pageable параметры паджинации
     * @return {@link Page<AccountDTO>} страница с информацией о счетах пользователя
     */
    @ApiOperation(value = "Получение перечня счетов пользователя постранично")
    @GetMapping(
            path = GET_ACCOUNTS_ROUTE,
            produces = APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Page<AccountDTO>> getCurrentUserAccounts(Long userId, Pageable pageable) throws TransactionManagerException {
        return ok(userInfoService.getUserAccounts(userId, pageable));
    }

    /**
     * Получение перечня счетов текущего пользователя постранично
     *
     * @param pageable параметры паджинации
     * @return {@link Page<AccountDTO>} страница с информацией о счетах пользователя
     */
    @ApiOperation(value = "Получение перечня счетов текущего пользователя постранично")
    @GetMapping(
            path = GET_CURRENT_ACCOUNTS_ROUTE,
            produces = APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Page<AccountDTO>> getCurrentUserAccounts(Pageable pageable) throws TransactionManagerException {
        return ok(userInfoService.getCurrenUserAccounts(pageable));
    }

    /**
     * Создание пользователя
     *
     * @param userInfoDTO данные пользователя
     * @return {@link UserInfoDTO} информация о созданном пользователе
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
     * Редактирование информации о пользователе
     *
     * @param userInfoDTO обновленные данные пользователя
     * @return {@link UserInfoDTO} обновленная информация о пользователе
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
    public ResponseEntity<UserInfoDTO> deleteUser(@PathVariable(ID) Long userId) throws TransactionManagerException {
        userInfoService.deleteUser(userId);
        return noContent().build();
    }

}
