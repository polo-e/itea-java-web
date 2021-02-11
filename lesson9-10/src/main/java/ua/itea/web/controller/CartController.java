package ua.itea.web.controller;

import ua.itea.dao.ProductsDAO;
import ua.itea.model.Product;
import ua.itea.web.dto.ProductsDAOMySql;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

public class CartController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        RequestDispatcher rd = req.getRequestDispatcher("./WEB-INF/view/cart.jsp");
        rd.forward(req, resp);
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        Map<Product, Integer> cart;

        if (req.getSession().getAttribute("cart") != null) {
            cart = (Map<Product, Integer>) req.getSession().getAttribute("cart");
        } else {
            cart = new HashMap<>();
        }

        ProductsDAO productsDAO = new ProductsDAOMySql();
        String productId = req.getParameter("productId");

        boolean deleteProduct = false;
        if (productId.startsWith("-")) {
            deleteProduct = true;
        }

        Product product = productsDAO.getProduct(productId.replaceAll("-", ""));

        Integer count = Integer.valueOf(req.getParameter("count"));

        Integer i = cart.get(product);

        if (i == null) {
            cart.put(product, count);
        } else if (!deleteProduct) {
            i = i + count;
            cart.put(product, i);
        } else {
            i = i - count;
            if (i > 0) {
                cart.put(product, i);
            } else {
                cart.remove(product);
            }
        }

        req.getSession().setAttribute("cart", cart);

        int newCount = 0;
        for (Integer c : cart.values()) {
            newCount += c;
        }

        req.getSession().setAttribute("count", newCount);
        resp.getWriter().write("" + newCount);
    }
}
