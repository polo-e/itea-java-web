package ua.itea.dao.impl;

import org.springframework.stereotype.Repository;
import ua.itea.dao.ProductsDao;
import ua.itea.model.Category;
import ua.itea.model.Product;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.List;

@Repository
public class ProductsHibernateImpl implements ProductsDao {

    private EntityManager entityManager = Persistence.createEntityManagerFactory("user").createEntityManager();

    public List<Product> getProducts(String categoryId) {

        Query query;
        if (categoryId != null){
            query = entityManager.createNamedQuery("Product.getCategory", Product.class);
            query.setParameter("category", new Category(Integer.parseInt(categoryId)));
        } else {
            query = entityManager.createNamedQuery("Product.getAll", Product.class);
        }
        return query.getResultList();
    }

    @Override
    public List<Category> getCategories() {
        return entityManager.createNamedQuery("Category.getAll", Category.class).getResultList();
    }

    public Product getProduct(String productId) {
        return entityManager.find(Product.class, Integer.valueOf(productId));
    }

}
