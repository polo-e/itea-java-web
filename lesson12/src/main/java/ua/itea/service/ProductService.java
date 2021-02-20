package ua.itea.service;

import ua.itea.model.Product;

import java.util.List;

public interface ProductService {
    List<Product> getProducts(String category);

    Product getProduct(String productId);
}
