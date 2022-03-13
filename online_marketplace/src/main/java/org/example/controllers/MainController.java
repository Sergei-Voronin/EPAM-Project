package org.example.controllers;

import org.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import javax.servlet.http.HttpServletRequest;


@Controller
public class MainController {

    @Autowired
    private UserService userService;

    @GetMapping("/")
    public String home(@AuthenticationPrincipal UserDetails currentUser, Model model, HttpServletRequest request) {
        model.addAttribute("title", "Главная страница");
        if (request.isUserInRole("ROLE_USER")) {
            model.addAttribute("user", "Пользователь");
        }
        else if (request.isUserInRole("ROLE_ADMIN")) {
            model.addAttribute("user", "Администратор");
        }
        else {
            model.addAttribute("user", "Незарегистрированный пользователь");
        }
        return "index";
    }
}