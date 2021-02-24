package ua.itea.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ShowController {

    @RequestMapping(value = "/")
    public String rootController() {
        return "index";
    }

    @RequestMapping("/cart")
    public String showCart() {
        return "cart";
    }
}
