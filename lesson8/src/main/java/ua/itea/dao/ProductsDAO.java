package ua.itea.dao;

import ua.itea.model.Product;

import java.util.List;

public interface ProductsDAO {
	List<Product> getProducts(String category);
	Product getProduct(String productId);
}
