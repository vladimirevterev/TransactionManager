package ru.sberbank.transactionmanager.web;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.sberbank.transactionmanager.common.dictionary.RoleDictionary;
import ru.sberbank.transactionmanager.common.error.Error;
import ru.sberbank.transactionmanager.common.error.TransactionManagerException;
import ru.sberbank.transactionmanager.domain.entity.user.Role;
import ru.sberbank.transactionmanager.domain.entity.user.UserInfo;
import ru.sberbank.transactionmanager.utils.ErrorHelper;
import ru.sberbank.transactionmanager.utils.UserHelper;

import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Главный контроллер для отображения web-страниц
 */
@Controller
@AllArgsConstructor
public class MainController {

    private UserHelper userHelper;

    @GetMapping(value = {"/", "/index"})
    public String index(Model model) throws TransactionManagerException {
        UserInfo currentUser = userHelper.getCurrentUser();
        if (Objects.isNull(currentUser)) {
            ErrorHelper.makeError(
                    "Текущий пользователь не задан",
                    Error.CURRENT_USER_NOT_SPECIFIED
            );
        }
        model.addAttribute("username", currentUser.firstName);
        model.addAttribute("fullname", currentUser.getFullName());
        model.addAttribute("roles", currentUser.getRoles());
        model.addAttribute("accounts", currentUser.getAccounts());
        if (currentUser.getRoles()
                .stream()
                .map(Role::getCode)
                .collect(Collectors.toSet())
                .contains(RoleDictionary.ADMIN.getCode())) {
            return "/adminindex";
        }
        return "/userindex";
    }

}
