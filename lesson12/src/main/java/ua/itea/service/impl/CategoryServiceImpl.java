package ua.itea.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.itea.dao.CategoryDao;
import ua.itea.model.Category;
import ua.itea.service.CategoryService;
import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryDao categoryDao;

    @Override
    public List<Category> getCategories() {
       return categoryDao.getCategories();
    }
}

