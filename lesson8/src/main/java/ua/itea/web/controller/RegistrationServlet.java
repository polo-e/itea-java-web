package ua.itea.web.controller;

import ua.itea.service.UserService;
import ua.itea.model.User;
import ua.itea.web.utils.Cryptor;
import ua.itea.web.utils.Validator;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;

public class RegistrationServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        /*
        Enumeration<String> names = req.getSession().getAttributeNames();
        while (names.hasMoreElements()) {
            String param = names.nextElement();
            System.out.println(param + " " + req.getSession().getAttribute(param));
        }
        */
        if (req.getSession().getAttribute("regCounter") == null) {
            req.getSession().setAttribute("regCounter", 0);
        }
        int regCounter = Integer.parseInt(req.getSession().getAttribute("regCounter").toString());

        if (regCounter >= 3) {
            req.getSession().setAttribute("isShowAntiBotForm", "true");

            if (req.getParameter("letters") != null) {
                if (req.getSession().getAttribute("generatedLetter").toString().replaceAll(" ", "")
                        .equals(req.getParameter("letters").replaceAll(" ", ""))) {

                    req.getSession().setAttribute("registrationResult", "Registration successful");
                    req.getSession().setAttribute("regCounter", 0);
                    req.getSession().setAttribute("isShowAntiBotForm", null);

                    RequestDispatcher rd = req.getRequestDispatcher("./WEB-INF/view/logout.jsp");
                    rd.forward(req, resp);
                    return;
                }
            }

            char[] array = new char[]{'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};
            StringBuilder sb = new StringBuilder();

            for (int i = 0; i < 6; i++) {
                int j = (int) (Math.random() * 26);
                char s = (j > 20) ? array[j] : Character.toUpperCase(array[j]);
                sb.append(s + " ");
            }
            req.getSession().setAttribute("generatedLetter", sb.toString());
        }
        RequestDispatcher rd = req.getRequestDispatcher("./WEB-INF/view/registration.jsp");
        rd.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        /*
        Enumeration<String> names = req.getSession().getAttributeNames();
        while (names.hasMoreElements()) {
            String param = names.nextElement();
            System.out.println(param + " " + req.getSession().getAttribute(param));
        }
        */

        String name =  req.getParameter("name");
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        String address = req.getParameter("address");
        String sex = req.getParameter("sex");
        String comment = req.getParameter("comment");

        req.getSession().setAttribute("sendLetters", req.getParameter("letters"));
        req.getSession().setAttribute("registrationResult", "");

        req.getSession().setAttribute("name", name);
        req.getSession().setAttribute("login", login);
        req.getSession().setAttribute("password", password);
        req.getSession().setAttribute("retypepassword", req.getParameter("retypepassword"));
        req.getSession().setAttribute("address", address);
        req.getSession().setAttribute("sex", sex);
        req.getSession().setAttribute("comment", comment);
        req.getSession().setAttribute("sendLetters", req.getParameter("letters"));
        req.getSession().setAttribute("registrationResult", "");

        StringBuilder errorMessage = new StringBuilder();
        boolean error = false;

        if (req.getSession().getAttribute("name") != null) {
            errorMessage.append("<ul>");

            if (req.getSession().getAttribute("name").toString().isEmpty()) {
                error = true;
                errorMessage.append("<li>Name field is empty</li>");
            }

            if (req.getSession().getAttribute("login").toString().isEmpty()) {
                error = true;
                errorMessage.append("<li>Login field is empty</li>");
            } else if (!Validator.isValidEmail(req.getSession().getAttribute("login").toString())) {
                error = true;
                errorMessage.append("<li>Entered incorrect login!");
            }

            if (req.getSession().getAttribute("password").toString().isEmpty()) {
                error = true;
                errorMessage.append("<li>Password field is empty</li>");

            } else if (!Validator.isValidPassword(req.getSession().getAttribute("password").toString())) {
                error = true;
                errorMessage.append("<li>Entered incorrect password! It must contains at least 1 digit, 1 lowercase, 1 uppercase & special character, mim 8 & max 20 characters</li>");
            }

            if (!req.getSession().getAttribute("password").toString().equals(req.getSession().getAttribute("retypepassword").toString())) {
                error = true;
                errorMessage.append("<li>Retype password error</li>");
            }

            if (req.getSession().getAttribute("address").toString().isEmpty()) {
                error = true;
                errorMessage.append("<li>Address field is empty</li>");
            }

            if (!error) {

                if (req.getSession().getAttribute("regCounter") == null) {
                    req.getSession().setAttribute("regCounter", 0);
                }

                int regCounter = Integer.parseInt(req.getSession().getAttribute("regCounter").toString());
                req.getSession().setAttribute("regCounter", ++regCounter);

                if (regCounter < 3) {

                    UserService userController = new UserService();

                    User user = null;
                    try {
                        user = new User()
                                .setName(name)
                                .setLogin(login)
                                .setPassword(Cryptor.encrypt(password))
                                .setAddress(address)
                                .setSex(sex)
                                .setComment(comment);
                    } catch (NoSuchAlgorithmException e) {
                        e.printStackTrace();
                    }

                    user = userController.registration(user);

                    if (user != null){
                        req.getSession().setAttribute("registrationResult", "Registration successful");
                        req.getSession().setAttribute("user", user);

                        RequestDispatcher rd = req.getRequestDispatcher("./WEB-INF/view/logout.jsp");
                        rd.forward(req, resp);

                        return;
                    }
                    errorMessage.append(String.format("Login %s already registered!", login));
                }
            }
            errorMessage.append("<ul>");
            req.getSession().setAttribute("errorMessage", errorMessage.toString());
        }
        doGet(req, resp);
    }
}