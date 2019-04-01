package ru.sberbank.transactionmanager.web;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.sberbank.transactionmanager.utils.UserHelper;

@Controller
@AllArgsConstructor
public class MainController {

    private UserHelper userHelper;

    @GetMapping(value = {"/", "/index"})
    public String index(Model model) {
        model.addAttribute("username", userHelper.getCurrentUser().firstName);
        return "/index";
    }

}
