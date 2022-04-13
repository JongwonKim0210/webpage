package com.webpage.domain.user.controller;

import com.webpage.domain.user.service.UserService;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@AllArgsConstructor
public class UserController {

    private UserService userService;

    @GetMapping("/login")
    public String loginPage(Model model) {
        return "template/login/LoginPage";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.removeAttribute("loginInfo");
        return "redirect:/";
    }

    @GetMapping("/reset")
    public String resetPassword(Model model) {
        return null;
    }

    @GetMapping("/create")
    public String createAccount(Model model) {
        return "template/login/UserCreate";
    }
}
