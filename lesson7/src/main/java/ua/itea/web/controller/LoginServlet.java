package ua.itea.web.controller;

import ua.itea.service.UserService;
import ua.itea.web.dto.User;
import ua.itea.web.utils.Cryptor;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.Enumeration;

public class LoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {

        /*
        Enumeration<String> names = req.getSession().getAttributeNames();
        while (names.hasMoreElements()) {
            String param = names.nextElement();
            System.out.println(param + "-> " + req.getSession().getAttribute(param));
        }
        */
        RequestDispatcher rd = req.getRequestDispatcher("./WEB-INF/view/login.jsp");
        rd.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {

        /*
        Enumeration<String> names = req.getSession().getAttributeNames();
        while (names.hasMoreElements()) {
            String param = names.nextElement();
            System.out.println(param + "-> " + req.getSession().getAttribute(param));
        }
        */

        if (req.getParameter("logout") != null) {
            req.getSession().removeAttribute("user");
            req.getSession().setAttribute("result", "");

            RequestDispatcher rd = req.getRequestDispatcher("./WEB-INF/view/login.jsp");
            rd.forward(req, resp);
        }

        String login = req.getParameter("login");
        String password = req.getParameter("password");

        if (req.getSession().getAttribute("counter") == null) {
            req.getSession().setAttribute("counter", 0);
        }

        String color = "brown";
        String access = "";

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
                user = new UserService().authorization(login, Cryptor.encrypt(password));
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            }

            if (user != null) {

                req.getSession().setAttribute("result", "");
                req.getSession().setAttribute("user", user);
                req.getSession().setAttribute("counter", 0);
                RequestDispatcher rd = req.getRequestDispatcher("./WEB-INF/view/logout.jsp");
                rd.forward(req, resp);

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
                req.getSession().setAttribute("result", String.format("<font color='%s'>%s</font>", color, access));
                RequestDispatcher rd = req.getRequestDispatcher("./WEB-INF/view/login.jsp");
                rd.forward(req, resp);
            }
        }
        req.getSession().setAttribute("result", String.format("<font color='%s'>%s</font>", color, access));
        RequestDispatcher rd = req.getRequestDispatcher("./WEB-INF/view/login.jsp");
        rd.forward(req, resp);
    }
}
