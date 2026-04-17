package storeapp.view;

import storeapp.domain.Category;
import storeapp.services.CategoryService;

import java.util.Scanner;

public class CategoryView {

    // Scanner sc = new Scanner(System.in);
    private final Scanner sc;
    private final CategoryService categoryService;

    public CategoryView(Scanner sc, CategoryService categoryService) {
    this.sc = sc;
    this.categoryService = categoryService;
    }

    public void createCategory() {
        Category category = categoryService.createCategory(new Category());
        System.out.println("Categoria creada: " + category.getIdCategory() + " - " + category.getDescription());
    }

    public void getAllCategories(){
        System.out.println("--- Listado de Categorias ---");
        categoryService.getAllCategories().forEach(c ->
                System.out.println(c.getIdCategory() + " | " + c.getDescription() + " | Estado: " + c.isState())
        );
    }

    public void getCategoryById() {
        System.out.println("Ingrese el id de la categoria: (0. Salir)");
        int id = sc.nextInt();
        sc.nextLine();
        if (id == 0){
            System.out.println("Regresando...");
            return;
        }
        categoryService.getCategoryById(id)
                .ifPresentOrElse(
                        c -> System.out.println(c.getIdCategory() + " | " + c.getDescription() + " | Estado: " + c.isState()),
                        () -> System.out.println("Categoria no encontrada")
                );
    }

    public void updateCategory() {
        System.out.println("Ingrese el id de la categoria a modificar: (0. Salir)");
        int id = sc.nextInt();
        sc.nextLine();
        if (id == 0){
            System.out.println("Regresando...");
            return;
        }
        categoryService.getCategoryById(id)
                .ifPresentOrElse(
                        c -> {
                            categoryService.updateCategory(c);
                            System.out.println("Categoria actualizada correctamente");
                        },
                        () -> System.out.println("Categoria no encontrada")
                );
    }

    public void deleteCategory() {
        System.out.println("Ingrese el id de la categoria a eliminar:");
        int id = sc.nextInt();
        sc.nextLine();
        boolean deleted = categoryService.deleteCategory(id);
        System.out.println(deleted ? "Categoria eliminada correctamente" : "Categoria no encontrada");
    }
}