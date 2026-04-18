package storeapp.utils;

import storeapp.exceptions.DuplicateIdException;
import storeapp.exceptions.DuplicateEmailException;
import storeapp.exceptions.ValidationException;
import storeapp.domain.Customer;
import storeapp.domain.Category;
import storeapp.domain.Product;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase de prueba rápida para demostrar el funcionamiento de validaciones
 * 
 * INSTRUCCIONES:
 * 1. Ejecuta la clase como aplicación principal (main)
 * 2. Verás ejemplos de todas las validaciones
 * 3. Prueba a modificar los valores para ver diferentes validaciones
 * 
 * NOTA: Esta es una clase de DEMOSTRACIÓN. NO es parte del sistema principal.
 */
public class ValidationTestDemo {

    public static void main(String[] args) {
        System.out.println("\n╔════════════════════════════════════════════════════╗");
        System.out.println("║  DEMO DE VALIDACIONES - LUCIASTORE                ║");
        System.out.println("╚════════════════════════════════════════════════════╝\n");

        // Demo 1: Entity Validator
        testEntityValidator();

        // Demo 2: Duplicate Checker
        testDuplicateChecker();

        // Demo 3: Excepciones personalizadas
        testExceptions();

        System.out.println("\n╔════════════════════════════════════════════════════╗");
        System.out.println("║  FIN DE LA DEMOSTRACIÓN                           ║");
        System.out.println("╚════════════════════════════════════════════════════╝\n");
    }

    /**
     * Demuestra el uso de EntityValidator
     */
    public static void testEntityValidator() {
        System.out.println("📋 PRUEBA 1: VALIDACIONES DE CAMPOS (EntityValidator)");
        System.out.println("═══════════════════════════════════════════════════\n");

        // Validar email
        System.out.println("✓ Email válido: " + EntityValidator.isValidEmail("usuario@ejemplo.com"));
        System.out.println("✗ Email inválido: " + EntityValidator.isValidEmail("usuariosindominio"));
        
        // Validar nombre
        System.out.println("✓ Nombre válido: " + EntityValidator.isValidName("Juan Carlos"));
        System.out.println("✗ Nombre muy corto: " + EntityValidator.isValidName("A"));
        
        // Validar precio
        System.out.println("✓ Precio válido (100.50): " + EntityValidator.isValidPrice(100.50));
        System.out.println("✗ Precio negativo (-50): " + EntityValidator.isValidPrice(-50));
        
        // Validar stock
        System.out.println("✓ Stock válido (10): " + EntityValidator.isValidStock(10));
        System.out.println("✓ Stock válido (0): " + EntityValidator.isValidStock(0));
        System.out.println("✗ Stock negativo (-5): " + EntityValidator.isValidStock(-5));
        
        // Validar contraseña
        System.out.println("✓ Contraseña válida: " + EntityValidator.isValidPassword("123456"));
        System.out.println("✗ Contraseña corta: " + EntityValidator.isValidPassword("123"));
        
        // Validar ID
        System.out.println("✓ ID válido (1): " + EntityValidator.isValidId(1));
        System.out.println("✗ ID negativo (-1): " + EntityValidator.isValidId(-1));

        System.out.println("\n");
    }

    /**
     * Demuestra el uso de DuplicateChecker
     */
    public static void testDuplicateChecker() {
        System.out.println("🔍 PRUEBA 2: DETECCIÓN DE DUPLICADOS (DuplicateChecker)");
        System.out.println("═══════════════════════════════════════════════════════\n");

        // Crear lista de clientes de prueba
        List<Customer> customers = new ArrayList<>();
        customers.add(new Customer(1, "Juan", "Pérez", "juan@email.com", "123456", true, 1000, "NUEVO"));
        customers.add(new Customer(2, "María", "García", "maria@email.com", "123456", true, 2000, "ANTIGUO"));

        // Verificar si existe cliente con ID
        System.out.println("¿Existe cliente ID=1? " + DuplicateChecker.customerExists(customers, 1));
        System.out.println("¿Existe cliente ID=10? " + DuplicateChecker.customerExists(customers, 10));

        // Verificar si existe cliente con email
        System.out.println("¿Existe cliente juan@email.com? " + 
            DuplicateChecker.customerEmailExists(customers, "juan@email.com"));
        System.out.println("¿Existe cliente inexistente@email.com? " + 
            DuplicateChecker.customerEmailExists(customers, "inexistente@email.com"));

        // Crear lista de categorías de prueba
        List<Category> categories = new ArrayList<>();
        categories.add(new Category(1, "Deportiva", true));
        categories.add(new Category(2, "Casual", true));

        System.out.println("¿Existe categoría ID=1? " + DuplicateChecker.categoryExists(categories, 1));
        System.out.println("¿Existe categoría ID=5? " + DuplicateChecker.categoryExists(categories, 5));

        System.out.println("\n");
    }

    /**
     * Demuestra el uso de excepciones personalizadas
     */
    public static void testExceptions() {
        System.out.println("⚠️  PRUEBA 3: EXCEPCIONES PERSONALIZADAS");
        System.out.println("══════════════════════════════════════════\n");

        List<Customer> customers = new ArrayList<>();
        customers.add(new Customer(1, "Juan", "Pérez", "juan@email.com", "123456", true, 1000, "NUEVO"));

        // Ejemplo 1: Intento de ID duplicado
        System.out.println("Intento 1: Crear cliente con ID duplicado");
        try {
            DuplicateChecker.checkCustomerDuplicateById(customers, 1);
        } catch (DuplicateIdException e) {
            System.out.println("  ❌ DuplicateIdException capturada: " + e.getMessage());
        }

        // Ejemplo 2: Intento de email duplicado
        System.out.println("\nIntento 2: Crear cliente con email duplicado");
        try {
            DuplicateChecker.checkCustomerDuplicateByEmail(customers, "juan@email.com");
        } catch (DuplicateEmailException e) {
            System.out.println("  ❌ DuplicateEmailException capturada: " + e.getMessage());
        }

        // Ejemplo 3: Validación fallida
        System.out.println("\nIntento 3: Validación de email inválido");
        try {
            EntityValidator.validateEmailFormat("correosindominio");
        } catch (ValidationException e) {
            System.out.println("  ❌ ValidationException capturada: " + e.getMessage());
        }

        // Ejemplo 4: Validación de precio negativo
        System.out.println("\nIntento 4: Validación de precio negativo");
        try {
            EntityValidator.validatePositivePrice(-100);
        } catch (ValidationException e) {
            System.out.println("  ❌ ValidationException capturada: " + e.getMessage());
        }

        // Ejemplo 5: Validación de campo vacío
        System.out.println("\nIntento 5: Validación de campo vacío");
        try {
            EntityValidator.validateNotEmpty("", "nombre");
        } catch (ValidationException e) {
            System.out.println("  ❌ ValidationException capturada: " + e.getMessage());
        }

        // Ejemplo 6: Success case
        System.out.println("\nIntento 6: Crear cliente con datos válidos");
        try {
            DuplicateChecker.checkCustomerDuplicateById(customers, 999); // ID nuevo
            DuplicateChecker.checkCustomerDuplicateByEmail(customers, "nuevo@email.com"); // Email nuevo
            EntityValidator.validateEmailFormat("nuevo@email.com");
            EntityValidator.validatePositivePrice(1000);
            System.out.println("  ✅ Todas las validaciones pasaron correctamente");
            System.out.println("  ✅ El cliente PUEDE ser creado");
        } catch (Exception e) {
            System.out.println("  ❌ Error: " + e.getMessage());
        }

        System.out.println("\n");
    }
}

