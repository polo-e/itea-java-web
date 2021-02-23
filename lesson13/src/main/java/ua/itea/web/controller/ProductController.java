package ua.itea.web.controller;

import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

public interface ProductController {

    String showProducts(@RequestParam(name = "category", required = false) String category, HttpSession session);
}
