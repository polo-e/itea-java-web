package ua.itea.servlet;

import ua.itea.web.controller.UserController;
import ua.itea.web.dto.User;
import ua.itea.web.utils.Cryptor;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.Date;

public class LoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.sendRedirect("/handler.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        /*
        Enumeration<String> names = req.getSession().getAttributeNames();
        while (names.hasMoreElements()) {
            String param = names.nextElement();
            System.out.println(param + " " + req.getSession().getAttribute(param));
        }
        */

        if (req.getParameter("logout") != null) {
            req.getSession().setAttribute("name", null);
            req.getSession().setAttribute("result", "");
            resp.sendRedirect("/handler.jsp");
        }

        String login = req.getParameter("login");
        String password = req.getParameter("password");

        if (req.getSession().getAttribute("counter") == null) {
            req.getSession().setAttribute("counter", 0);
        }

        String color = "brown";
        String access = "";

        // Counter of invalid login attempts
        //TODO move authorization logic to Filter
        if (Integer.parseInt(req.getSession().getAttribute("counter").toString()) >= 3) {
            // Delay time
            Long blockedDiffTime = Long.valueOf(10 * 1000);
            long currenTime = new Date().getTime();

            //System.out.println("Time diff: " + ((currenTime - Long.parseLong(req.getSession().getAttribute("blockedTime").toString())) / 1000.0));

            if (currenTime - (Long.parseLong(req.getSession().getAttribute("blockedTime").toString()) + blockedDiffTime) >= 0) {
                req.getSession().setAttribute("counter", 0);
                req.getSession().setAttribute("blockedTime", null);
                req.getSession().setAttribute("disableForm", null);
            } else {
                access = "Access blocked. Please try again later.";
            }
        }

        if (login != null && password != null) {

            User user = null;
            try {
                user = new UserController().authorization(login, Cryptor.encrypt(password));
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            }

            if (user != null) {
                req.getSession().setAttribute("name", user.getName());
                req.getSession().setAttribute("counter", 0);
                resp.sendRedirect("/logout.jsp");

            } else {
                int counter = Integer.parseInt(req.getSession().getAttribute("counter").toString());
                req.getSession().setAttribute("counter", ++counter);
                access = "Access denied";

                if (counter >= 3) {
                    color = "red";

                    if (req.getSession().getAttribute("blockedTime") == null) {
                        req.getSession().setAttribute("blockedTime", new Date().getTime());
                        req.getSession().setAttribute("disableForm", "true");
                    }
                    access = "Access blocked. Please try again later.";
                }
                resp.sendRedirect("/handler.jsp");
            }
        }
        req.getSession().setAttribute("result", String.format("<font color='%s'>%s</font>", color, access));
    }
}
