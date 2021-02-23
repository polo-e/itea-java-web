package ua.itea.service;

import ua.itea.model.User;

public interface UserService {

    User authorization(String login, String password);
    User registration(User user);
}
