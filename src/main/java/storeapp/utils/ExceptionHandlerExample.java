package storeapp.utils;

import storeapp.exceptions.DuplicateIdException;
import storeapp.exceptions.DuplicateEmailException;
import storeapp.exceptions.ValidationException;

/**
 * Clase de ejemplo para manejar las excepciones de validación
 * Puedes usar estos patrones en tus servicios o en la UI (MenuApp)
 * 
 * Ejemplo de uso en un servicio:
 * 
 * try {
 *     customer = custumerServiceImpl.createCustomer(new Customer());
 * } catch (DuplicateIdException e) {
 *     System.out.println("El cliente con ese ID ya existe");
 *     logger.error("Duplicated customer ID", e);
 * } catch (DuplicateEmailException e) {
 *     System.out.println("El email ya está registrado");
 *     logger.error("Duplicated email", e);
 * } catch (ValidationException e) {
 *     System.out.println("Datos inválidos: " + e.getMessage());
 *     logger.warn("Validation error", e);
 * } catch (Exception e) {
 *     System.out.println("Error inesperado");
 *     logger.error("Unexpected error", e);
 * }
 */
public class ExceptionHandlerExample {

    /**
     * Ejemplo de manejador global para crear clientes con manejo de errores
     */
    public static void exampleCreateCustomerWithErrorHandling() {
        System.out.println("\n=== EJEMPLO DE MANEJO DE EXCEPCIONES ===\n");
        
        System.out.println("1. INTENTO DE CREAR CLIENTE CON ID DUPLICADO:");
        try {
            // Simulación de error de ID duplicado
            throw new DuplicateIdException("Ya existe un cliente registrado con el ID: 1");
        } catch (DuplicateIdException e) {
            System.out.println("❌ Error específico capturado: " + e.getMessage());
            System.out.println("   Acción: Solicitar nuevo ID al usuario");
        }
        
        System.out.println("\n2. INTENTO DE CREAR CLIENTE CON EMAIL DUPLICADO:");
        try {
            // Simulación de error de email duplicado
            throw new DuplicateEmailException("Ya existe un cliente registrado con el email: test@example.com");
        } catch (DuplicateEmailException e) {
            System.out.println("❌ Error específico capturado: " + e.getMessage());
            System.out.println("   Acción: Solicitar nuevo email al usuario");
        }
        
        System.out.println("\n3. INTENTO DE CREAR CLIENTE CON DATOS INVÁLIDOS:");
        try {
            // Simulación de error de validación
            throw new ValidationException("El email ingresado no tiene un formato válido");
        } catch (ValidationException e) {
            System.out.println("❌ Error de validación capturado: " + e.getMessage());
            System.out.println("   Acción: Mostrar formato válido y reintentar");
        }
    }
    
    /**
     * Método helper para registrar errores de forma consistente
     */
    public static void logValidationError(String operation, Exception e) {
        if (e instanceof DuplicateIdException) {
            System.out.println("[ERROR-DUPLICATE] " + operation + ": " + e.getMessage());
        } else if (e instanceof DuplicateEmailException) {
            System.out.println("[ERROR-EMAIL] " + operation + ": " + e.getMessage());
        } else if (e instanceof ValidationException) {
            System.out.println("[ERROR-VALIDATION] " + operation + ": " + e.getMessage());
        } else {
            System.out.println("[ERROR-UNKNOWN] " + operation + ": " + e.getMessage());
        }
    }
    
    /**
     * Patrón Try-Catch recomendado para servicios
     */
    public static String getErrorMessage(Exception e) {
        if (e instanceof DuplicateIdException) {
            return "⚠️ Este ID ya está en uso. Por favor use otro ID.";
        } else if (e instanceof DuplicateEmailException) {
            return "⚠️ Este email ya está registrado. Por favor use otro email.";
        } else if (e instanceof ValidationException) {
            return "⚠️ Datos inválidos: " + e.getMessage();
        } else {
            return "⚠️ Error inesperado: " + e.getMessage();
        }
    }
}

