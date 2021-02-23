package ua.itea.web.controller.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import ua.itea.model.Product;
import ua.itea.service.ProductService;
import ua.itea.web.controller.CartController;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.*;

@Controller
public class CartControllerImpl implements CartController {

    @Autowired
    ProductService productService;

    @RequestMapping("/cart")
    public String showCart() {
        return "cart";
    }

    @RequestMapping(method = RequestMethod.POST, value = "/cart")
    public void addToCart(@RequestParam("productId") String productId, @RequestParam("count") String count,
                          HttpSession session, HttpServletResponse response) throws IOException {

        Map<Product, Integer> cart;

        if (session.getAttribute("cart") != null) {
            cart = (Map<Product, Integer>) session.getAttribute("cart");
        } else {
            cart = new HashMap<>();
        }

        boolean deleteProduct = false;
        if (productId.startsWith("-")) {
            deleteProduct = true;
        }

        Product product = productService.getProduct(productId.replaceAll("-", ""));

        Integer cnt = Integer.valueOf(count);

        Integer i = cart.get(product);

        if (i == null) {
            cart.put(product, cnt);
        } else if (!deleteProduct) {
            i = i + cnt;
            cart.put(product, i);
        } else {
            i = i - cnt;
            if (i > 0) {
                cart.put(product, i);
            } else {
                cart.remove(product);
            }
        }

        session.setAttribute("cart", cart);

        int newCount = 0;
        for (Integer c : cart.values()) {
            newCount += c;
        }

        session.setAttribute("count", newCount);
        response.getWriter().write("" + newCount);
    }
}
