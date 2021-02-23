package ua.itea.web.controller;

import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public interface CartController {

    String showCart();
    void addToCart(@RequestParam("productId") String productId, @RequestParam("count") String count,
                   HttpSession session, HttpServletResponse response) throws IOException;
}
