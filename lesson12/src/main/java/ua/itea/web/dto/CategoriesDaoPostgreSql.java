package ua.itea.web.dto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ua.itea.dao.CategoryDao;
import ua.itea.model.Category;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@Component
public class CategoriesDaoPostgreSql implements CategoryDao {

    private static final String SELECT_ALL = "SELECT * from category";

	@Autowired
	private DataSource dataSource;

	@Override
	public List<Category> getCategories() {
		return getProductsData(SELECT_ALL);
	}

	private List<Category> getProductsData(String query) {

		List<Category> categories = new ArrayList<>();

		try (Connection connection = dataSource.getConnection();
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