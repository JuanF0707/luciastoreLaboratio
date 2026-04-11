package storeapp.services;

import storeapp.domain.Category;
import storeapp.repository.CategoryRepository;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;


    public class CategoryServicesImpl implements CategoryService {

        Scanner sc = new Scanner(System.in);
        private final CategoryRepository categoryRepository;


        public CategoryServicesImpl (CategoryRepository categoryRepository){
            this.categoryRepository = categoryRepository;
        }

        @Override
        public Category createCategory(Category category){
            System.out.println("Ingrese el id de la categoria");
            int id = sc.nextInt();
            sc.nextLine();
            category.setIdCategory(id);

            System.out.println("Ingrese la descripción de la categoria");
            String description = sc.nextLine();
            category.setDescription(description);

            category.setState(true);

            return categoryRepository.saveCategory(category);
        }

        @Override
        public List<Category> getAllCategories() {
            return categoryRepository.findAllCategories();
        }

        @Override
        public Optional<Category> getCategoryById(int id) {
            return categoryRepository.findById(id);
        }

        @Override
        public Category updateCategory(Category category) {
            System.out.println("Ingrese la nueva descripcion:");
            String description = sc.nextLine();
            category.setDescription(description);

            System.out.println("Estado activo? (true/false):");
            boolean state = sc.nextBoolean();
            category.setState(state);

            return categoryRepository.updateCategory(category)
                    .orElse(null);
        }

         @Override
         public boolean deleteCategory(int id) {
            return categoryRepository.deleteById(id);
        }
    }

