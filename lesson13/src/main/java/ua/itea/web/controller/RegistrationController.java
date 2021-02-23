package ua.itea.web.controller;

import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

public interface RegistrationController {

    String showRegistrationForm (@RequestParam(value = "letters", required = false) String letters, HttpSession session);
    String registration(@RequestParam("name") String name, @RequestParam("login") String login, @RequestParam("password") String password,
                        @RequestParam("retypepassword") String retypepassword, @RequestParam("address") String address, @RequestParam("sex") String sex,
                        @RequestParam("comment") String comment, HttpSession session);
}
