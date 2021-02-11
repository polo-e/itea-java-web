package ua.itea.web.controller;

import ua.itea.dao.CategoryDAO;
import ua.itea.dao.ProductsDAO;
import ua.itea.model.Category;
import ua.itea.model.Product;
import ua.itea.web.dto.CategoriesDAOMySql;
import ua.itea.web.dto.ProductsDAOMySql;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class ProductController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        if (req.getSession().getAttribute("count") == null){
            req.getSession().setAttribute("count", 0);
        }

        CategoryDAO cd = new CategoriesDAOMySql();
        List<Category> categories = cd.getCategories();
        req.setAttribute("categories", categories);

        ProductsDAO pd = new ProductsDAOMySql();
        List<Product> products = pd.getProducts(req.getParameter("category"));
        req.setAttribute("products", products);

        RequestDispatcher rd = req.getRequestDispatcher("./WEB-INF/view/products.jsp");
        rd.forward(req, resp);
    }
}
