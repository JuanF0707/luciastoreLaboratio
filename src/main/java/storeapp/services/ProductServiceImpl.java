package storeapp.services;


import storeapp.domain.Product;
import storeapp.repository.CategoryRepository;
import storeapp.repository.ProductRepository;
import storeapp.utils.DuplicateChecker;
import storeapp.utils.EntityValidator;
import storeapp.exceptions.DuplicateIdException;
import storeapp.exceptions.ValidationException;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class ProductServiceImpl implements ProductService {

    Scanner sc = new Scanner(System.in);
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final StateSelector stateSelector = new StateSelector();

    public ProductServiceImpl(ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Product createProduct(Product product) {
        try {
            System.out.println("Ingrese el id del producto:");
            int id = sc.nextInt();
            sc.nextLine();
            
            // Validar que el ID sea positivo
            EntityValidator.validatePositiveId(id, "ID del producto");
            
            // Verificar si el ID ya existe
            DuplicateChecker.checkProductDuplicateById(productRepository.findAllProducts(), id);
            product.setIdProduct(id);

            System.out.println("Ingrese la descripcion del producto:");
            String description = sc.nextLine();
            
            // Validar que la descripción no esté vacía
            EntityValidator.validateNotEmpty(description, "descripción del producto");
            product.setDescription(description);

            System.out.println("Ingrese el precio del producto:");
            double price = sc.nextDouble();
            
            // Validar que el precio sea válido
            EntityValidator.validatePositivePrice(price);
            product.setPrice(price);

            System.out.println("Ingrese el stock del producto:");
            int stock = sc.nextInt();
            sc.nextLine();
            
            // Validar que el stock sea válido
            EntityValidator.validateNonNegativeStock(stock);
            product.setStock(stock);

            boolean state = stateSelector.ProductState();
            product.setState(state);

            System.out.println("--- Categorias disponibles ---");
            categoryRepository.findAllCategories().forEach(c ->
                    System.out.println(c.getIdCategory() + " | " + c.getDescription())
            );
            System.out.println("Ingrese el id de la categoria:");
            int categoryId = sc.nextInt();
            sc.nextLine();
            categoryRepository.findById(categoryId)
                    .ifPresentOrElse(
                            product::setCategory,
                            () -> System.out.println("Categoria no encontrada, se asignara sin categoria")
                    );

            Product savedProduct = productRepository.saveProduct(product);
            System.out.println("✓ Producto registrado exitosamente con ID: " + id);
            return savedProduct;
            
        } catch (DuplicateIdException e) {
            System.out.println("✗ Error: " + e.getMessage());
            throw e;
        } catch (ValidationException e) {
            System.out.println("✗ Error de validación: " + e.getMessage());
            throw e;
        } catch (Exception e) {
            System.out.println("✗ Error inesperado: " + e.getMessage());
            throw new ValidationException("Error al crear el producto: " + e.getMessage());
        }
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAllProducts();
    }

    @Override
    public Optional<Product> getProductById(int id) {
        return productRepository.findById(id);
    }

    @Override
    public Product updateProduct(Product product) {
        try {
            System.out.println("Ingrese la nueva descripcion:");
            String description = sc.nextLine();
            
            // Validar que la descripción no esté vacía
            EntityValidator.validateNotEmpty(description, "descripción");
            product.setDescription(description);

            System.out.println("Ingrese el nuevo precio:");
            double price = sc.nextDouble();
            
            // Validar que el precio sea válido
            EntityValidator.validatePositivePrice(price);
            product.setPrice(price);

            System.out.println("Ingrese el nuevo stock:");
            int stock = sc.nextInt();
            sc.nextLine();
            
            // Validar que el stock sea válido
            EntityValidator.validateNonNegativeStock(stock);
            product.setStock(stock);

            boolean state = stateSelector.ProductState();
            product.setState(state);

            Product updatedProduct = productRepository.updateProduct(product)
                    .orElseThrow(() -> new ValidationException("No se encontró el producto a actualizar"));
            System.out.println("✓ Producto actualizado exitosamente");
            return updatedProduct;
            
        } catch (ValidationException e) {
            System.out.println("✗ Error de validación: " + e.getMessage());
            throw e;
        } catch (Exception e) {
            System.out.println("✗ Error al actualizar el producto: " + e.getMessage());
            throw new ValidationException("Error al actualizar el producto");
        }
    }

    @Override
    public boolean deleteProduct(int id) {
        return productRepository.deleteById(id);
    }
}
