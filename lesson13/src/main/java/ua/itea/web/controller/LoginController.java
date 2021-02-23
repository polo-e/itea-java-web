package ua.itea.web.controller;

import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.sql.SQLException;

public interface LoginController {

    String showLoginForm() throws SQLException;
    String login(@RequestParam("login") String login, @RequestParam("password") String password,
                 HttpSession session);
    String logout(HttpSession session);
}
