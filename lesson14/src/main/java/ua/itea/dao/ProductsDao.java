package ua.itea.dao;

import ua.itea.model.Category;
import ua.itea.model.Product;

import java.util.List;

public interface ProductsDao {
	List<Product> getProducts(String categoryId);
	List<Category> getCategories();

	Product getProduct(String productId);

}
