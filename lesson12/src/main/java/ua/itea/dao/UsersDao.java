package ua.itea.dao;

import ua.itea.model.User;

public interface UsersDao {
    User getUser(String login, String password);
    User addUser(User user);
}
