package ua.itea.service;

import ua.itea.web.dto.ConnectionFactory;
import ua.itea.web.dto.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class UserService {

    private static final String SQL_SELECT = "SELECT * FROM users WHERE login = ? AND password = ?	";
    private static final String SQL_INSERT = "INSERT INTO users (name, login, password, address, sex, comment) VALUES (?,?,?,?,?,?)";


    public UserService() {
    }

    public User authorization(String login, String password) {

        User user = null;

        try (Connection connection = ConnectionFactory.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT);) {

            preparedStatement.setString(1, login);
            preparedStatement.setString(2, password);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                user = new User()
                        .setLogin(login)
                        .setPassword(password)
                        .setName(resultSet.getString("name"))
                        .setAddress(resultSet.getString("address"))
                        .setSex(resultSet.getString("sex"))
                        .setComment(resultSet.getString("comment"));
            }

            if (resultSet != null) {
                resultSet.close();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    public User registration(User user) {

        int row;

        try (Connection connection = ConnectionFactory.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL_INSERT)) {

            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getLogin());
            preparedStatement.setString(3, user.getPassword());
            preparedStatement.setString(4, user.getAddress());
            preparedStatement.setString(5, user.getSex());
            preparedStatement.setString(6, user.getComment());

            row = preparedStatement.executeUpdate();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;
        }

        if (row != 1) {
            return null;
        }
        return user;
    }
}

