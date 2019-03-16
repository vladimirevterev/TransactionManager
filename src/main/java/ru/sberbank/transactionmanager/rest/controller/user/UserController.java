package ru.sberbank.transactionmanager.rest.controller.user;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
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

    private static final String USER_INFO_ROUTE = "/{id}";

    @Autowired
    private UserInfoService userInfoService;

    @ApiOperation(value = "Получение информации о пользователе", response = UserInfoDTO.class)
    @GetMapping(
            path = USER_INFO_ROUTE,
            produces = APPLICATION_JSON_VALUE
    )
    public ResponseEntity<UserInfoDTO> getUserInfo(@PathVariable Long id) {
        return ok(userInfoService.getUserInfo(id));
    }

}
