package storeapp.repository;

import storeapp.domain.Category;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class CategoryRepository {

    List<Category> categories = new ArrayList<>(Arrays.asList(
            new Category(1, "Deportiva", true),
            new Category(2, "Casual", true),
            new Category(3, "Elegante", true)
    ));

    public Category saveCategory (Category category){
        categories.add(category);

        return category;
    }

    public List<Category> findAllCategories(){
        return categories;
    }

    public Optional<Category> findById(int id){
        for (Category category: categories) {
            if (category.getIdCategory() == id) {
                return Optional.of(category);
            }
        }
        return Optional.empty();
    }

    public Optional<Category> updateCategory(Category updateCategory) {
        for (int i = 0; i < categories.size(); i++){
            if (categories.get(i).getIdCategory() == updateCategory.getIdCategory()){
                categories.set(i, updateCategory);
                return Optional.of(updateCategory);
            }
        }
        return Optional.empty();
    }


     public boolean deleteById (int id){
         return categories.removeIf(c -> c.getIdCategory() == id);
    }




}
