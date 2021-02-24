package ua.itea.web.controller;

import org.springframework.web.bind.annotation.RequestBody;
import ua.itea.model.CartWrapper;

import javax.servlet.http.HttpSession;

public interface CartController {

    CartWrapper addToCart(@RequestBody CartWrapper cartWrapper, HttpSession session);
    CartWrapper delFromCart(@RequestBody CartWrapper cartWrapper, HttpSession session);

}
