package ua.itea.web.controller.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ua.itea.model.CartWrapper;
import ua.itea.model.Product;
import ua.itea.service.ProductService;
import ua.itea.web.controller.CartController;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;


@RestController
@RequestMapping("/api/v1/cart/")
public class CartControllerImpl implements CartController {

    @Autowired
    ProductService productService;

    @Override
    @PostMapping("/add")
    public CartWrapper addToCart(@RequestBody CartWrapper cartWrapper, HttpSession session) {
        return handleCart(cartWrapper, session, false);
    }

    @Override
    @DeleteMapping("/del")
    public CartWrapper delFromCart(@RequestBody CartWrapper cartWrapper, HttpSession session) {
        return handleCart(cartWrapper, session, true);
    }

    private CartWrapper handleCart(CartWrapper cartWrapper, HttpSession session, boolean isDelete){
        Map<Product, Integer> cart;

        if (session.getAttribute("cart") != null) {
            cart = (Map<Product, Integer>) session.getAttribute("cart");
        } else {
            cart = new HashMap<>();
        }

        Product product = productService.getProduct(cartWrapper.getProductId());

        Integer cnt = Integer.valueOf(cartWrapper.getCount());
        Integer i = cart.get(product);

        if (i == null) {
            cart.put(product, cnt);
        } else {
            i = i - cnt;
            if (i > 0) {
                cart.put(product, i);
            } else {
                cart.remove(product);
                cartWrapper.setReloadPage(true);
            }
        }

        session.setAttribute("cart", cart);

        int newCount = 0;
        for (Integer c : cart.values()) {
            newCount += c;
        }

        cartWrapper.setCount(newCount + "");

        if (isDelete){
            cartWrapper.setCount(i + "");
        }
        cartWrapper.setTotal(newCount + "");
        session.setAttribute("count", newCount);
        return cartWrapper;
    }
}