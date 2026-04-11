package storeapp.services;

import storeapp.domain.Category;

import java.util.List;
import java.util.Optional;

public interface CategoryService {

    Category createCategory(Category category);
    List<Category> getAllCategories();
    Optional<Category> getCategoryById(int id);
    Category updateCategory(Category category);
    boolean deleteCategory(int id);
}
