package ua.itea.web.dto;

import ua.itea.dao.CategoryDAO;
import ua.itea.model.Category;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CategoriesDAOMySql implements CategoryDAO {

    private static final String SELECT_ALL = "SELECT * from category";

	@Override
	public List<Category> getCategories() {
		return getProductsData(SELECT_ALL);
	}

	private List<Category> getProductsData(String query) {

		List<Category> categories = new ArrayList<>();

		try (Connection connection = ConnectionFactory.getInstance().getConnection();
			 Statement statement = connection.createStatement()) {

			ResultSet resultSet = statement.executeQuery(query);

			while (resultSet.next()) {
				categories.add(new Category(resultSet.getInt("id"), resultSet.getString("name")));
			}

			if (resultSet != null) {
				resultSet.close();
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return categories;
	}
}