package ua.itea.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.itea.dao.ProductsDao;
import ua.itea.model.Product;
import ua.itea.service.ProductService;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductsDao productsDao;

    public List<Product> getProducts(String category) {
        return productsDao.getProducts(category);
    }

    public Product getProduct(String productId) {
        return productsDao.getProduct(productId);
    }

}

