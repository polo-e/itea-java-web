package ua.itea.web.controller;

import ua.itea.dao.ProductsDAO;
import ua.itea.model.Product;
import ua.itea.web.dto.ProductsDAOMySql;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CartController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {

    	List<Product> cart;
        if (req.getSession().getAttribute("cart") != null) {
            cart = (List<Product>) req.getSession().getAttribute("cart");
        } else {
            cart = new ArrayList<>();
        }

        ProductsDAO productsDAO = new ProductsDAOMySql();
        String productId = req.getParameter("productId");

        Product product = productsDAO.getProduct(productId);
        cart.add(product);

        req.getSession().setAttribute("cart", cart);
        req.getSession().setAttribute("cartSize", cart.size());

        resp.sendRedirect("./products");
    }
}
