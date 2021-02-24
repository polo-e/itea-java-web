package ua.itea.web.controller.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import ua.itea.service.UserService;
import ua.itea.model.User;
import ua.itea.web.controller.LoginController;
import ua.itea.web.utils.Cryptor;

import javax.servlet.http.HttpSession;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.Date;


@Controller
public class LoginControllerImpl implements LoginController {

    @Autowired
    UserService userService;

    @Override
    @RequestMapping(value = "/login")
    public String showLoginForm() throws SQLException {
        return "login";
    }

    @Override
    @RequestMapping(value = "/login", method = RequestMethod.POST, params = {"login", "password"})
    public String login(@RequestParam("login") String login, @RequestParam("password") String password,
                        HttpSession session) {

        if (session.getAttribute("counter") == null) {
            session.setAttribute("counter", 0);
        }

        String color = "brown";
        String access = "Access denied";

        if (login != null && password != null) {

            User user = null;
            try {
                user = userService.authorization(login, Cryptor.encrypt(password));

            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            }

            if (user != null && session.getAttribute("blockedTime") == null) {

                session.setAttribute("user", user);
                session.removeAttribute("result");
                session.removeAttribute("counter");
                return "login";
            }

            int counter = Integer.parseInt(session.getAttribute("counter").toString());
            session.setAttribute("counter", ++counter);

            if (counter >= 3) {

                long currenTime = new Date().getTime();
                Long blockedDiffTime = Long.valueOf(10 * 1000);
                color = "red";
                access = "Access blocked. Please try again later.";

                if (session.getAttribute("blockedTime") != null) {
                    if (currenTime - (Long.parseLong(session.getAttribute("blockedTime").toString()) + blockedDiffTime) >= 0) {
                        session.setAttribute("counter", 0);
                        session.removeAttribute("blockedTime");
                        access = "";
                    }
                } else {
                    session.setAttribute("blockedTime", new Date().getTime());
                }
            }
        }
        session.setAttribute("result", String.format("<font color='%s'>%s</font>", color, access));
        return "login";
    }

    @Override
    @RequestMapping(value = "/logout")
    public String logout(HttpSession session) {

        session.removeAttribute("user");
        return "login";
    }
}