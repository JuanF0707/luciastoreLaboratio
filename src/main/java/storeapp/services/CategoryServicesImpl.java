package storeapp.services;

import storeapp.domain.Category;
import storeapp.repository.CategoryRepository;
import storeapp.utils.DuplicateChecker;
import storeapp.utils.EntityValidator;
import storeapp.exceptions.DuplicateIdException;
import storeapp.exceptions.ValidationException;

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
            try {
                System.out.println("Ingrese el id de la categoria");
                int id = sc.nextInt();
                sc.nextLine();
                
                // Validar que el ID sea positivo
                EntityValidator.validatePositiveId(id, "ID de la categoría");
                
                // Verificar si el ID ya existe
                DuplicateChecker.checkCategoryDuplicateById(categoryRepository.findAllCategories(), id);
                category.setIdCategory(id);

                System.out.println("Ingrese la descripción de la categoria");
                String description = sc.nextLine();
                
                // Validar que la descripción no esté vacía
                EntityValidator.validateNotEmpty(description, "descripción de la categoría");
                category.setDescription(description);

                category.setState(true);

                Category savedCategory = categoryRepository.saveCategory(category);
                System.out.println("✓ Categoría registrada exitosamente con ID: " + id);
                return savedCategory;
                
            } catch (DuplicateIdException e) {
                System.out.println("✗ Error: " + e.getMessage());
                throw e;
            } catch (ValidationException e) {
                System.out.println("✗ Error de validación: " + e.getMessage());
                throw e;
            } catch (Exception e) {
                System.out.println("✗ Error inesperado: " + e.getMessage());
                throw new ValidationException("Error al crear la categoría: " + e.getMessage());
            }
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
            try {
                System.out.println("Ingrese la nueva descripcion:");
                String description = sc.nextLine();
                
                // Validar que la descripción no esté vacía
                EntityValidator.validateNotEmpty(description, "descripción");
                category.setDescription(description);

                System.out.println("Estado activo? (true/false):");
                boolean state = sc.nextBoolean();
                category.setState(state);

                Category updatedCategory = categoryRepository.updateCategory(category)
                        .orElseThrow(() -> new ValidationException("No se encontró la categoría a actualizar"));
                System.out.println("✓ Categoría actualizada exitosamente");
                return updatedCategory;
                
            } catch (ValidationException e) {
                System.out.println("✗ Error de validación: " + e.getMessage());
                throw e;
            } catch (Exception e) {
                System.out.println("✗ Error al actualizar la categoría: " + e.getMessage());
                throw new ValidationException("Error al actualizar la categoría");
            }
        }

         @Override
         public boolean deleteCategory(int id) {
            return categoryRepository.deleteById(id);
        }
    }

