package ua.itea.web.dto;

import ua.itea.dao.ProductsDAO;
import ua.itea.model.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductsDAOMySql implements ProductsDAO {

    private static final String SELECT_ALL = "SELECT products.id, products.name, products.price, products.description, category.name AS category " +
            "FROM products JOIN category ON products.category_id = category.id";

    private static final String SELECT = "SELECT products.id, products.name, products.price, products.description, category.name AS category " +
			"FROM products JOIN category ON products.category_id = category.id " +
			"WHERE products.id = ";

    @Override
    public List<Product> getProducts(String category) {
		return getProductsData(SELECT_ALL + (category == null ? "" : " WHERE category.id = " + category));
    }

	@Override
	public Product getProduct(String productId) {
		return getProductsData(SELECT + productId).get(0);
	}

	private List<Product> getProductsData(String query) {

		List<Product> products = new ArrayList<>();

		try (Connection connection = ConnectionFactory.getInstance().getConnection();
			 Statement statement = connection.createStatement()) {

			ResultSet resultSet = statement.executeQuery(query);

			while (resultSet.next()) {

				Product product = new Product(resultSet.getInt("id"));

				product.setName(resultSet.getString("name"));
				product.setPrice(resultSet.getInt("price"));
				product.setDescription(resultSet.getString("description"));
				product.setCategory(resultSet.getString("category"));

				products.add(product);
			}

			if (resultSet != null) {
				resultSet.close();
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return products;
	}
}
