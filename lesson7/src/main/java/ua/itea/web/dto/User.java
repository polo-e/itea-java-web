package ua.itea.web.dto;

public class User {

    private String name;
    private String login;
    private String password;
    private String address;
    private String sex;
    private String comment;

    public User setName(String name) {
        this.name = name;
        return this;
    }

    public User setLogin(String login) {
        this.login = login;
        return this;
    }

    public User setPassword(String password) {
        this.password = password;
        return this;
    }

    public User setAddress(String address) {
        this.address = address;
        return this;
    }

    public User setSex(String sex) {
        this.sex = sex;
        return this;
    }

    public User setComment(String comment) {
        this.comment = comment;
        return this;
    }

    public String getName() {
        return name;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public String getAddress() {
        return address;
    }

    public String getSex() {
        return sex;
    }

    public String getComment() {
        return comment;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", address='" + address + '\'' +
                ", sex='" + sex + '\'' +
                ", comment='" + comment + '\'' +
                '}';
    }
}

