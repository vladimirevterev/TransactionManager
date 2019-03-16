package ru.sberbank.transactionmanager.controller;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.sberbank.transactionmanager.dto.user.UserInfoDto;
import ru.sberbank.transactionmanager.service.userinfo.UserInfoService;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.http.ResponseEntity.ok;

@RestController
@AllArgsConstructor
public class TransactionManagerController {

    public static final String USER_INFO_ROUTE = "/user/{id}";

    public static final String MONEY_TRANSFER_ROUTE = "/moneytransfer";

    @Autowired
    private UserInfoService userInfoService;

    @GetMapping(
            path = USER_INFO_ROUTE,
            produces = APPLICATION_JSON_VALUE
    )
    public ResponseEntity<UserInfoDto> getUserInfo(@RequestParam Long id) {
        return ok(userInfoService.getUserInfo(id));
    }



}
