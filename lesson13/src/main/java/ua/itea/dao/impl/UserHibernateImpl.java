package ua.itea.dao.impl;

import org.springframework.stereotype.Repository;
import ua.itea.dao.UsersDao;
import ua.itea.model.User;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.Query;

@Repository
public class UserHibernateImpl implements UsersDao {

    private EntityManager entityManager = Persistence.createEntityManagerFactory("user").createEntityManager();

    @Override
    public User getUser(String login, String password) {
        Query query = entityManager.createNamedQuery("User.find", User.class);
        query.setParameter("login", login);
        query.setParameter("password", password);

        User user = null;
        try {
            user = (User) query.getSingleResult();
        } catch (NoResultException e) {
        }
        return user;
    }

    @Override
    public User addUser(User user) {

        entityManager.getTransaction().begin();
        User savedUser = entityManager.merge(user);
        entityManager.getTransaction().commit();

        return savedUser;
    }
}
