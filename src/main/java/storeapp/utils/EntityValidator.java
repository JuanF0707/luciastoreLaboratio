package storeapp.utils;

import storeapp.exceptions.ValidationException;

/**
 * Clase utilitaria que centraliza todas las validaciones de entidades
 * Proporciona métodos estáticos para validar campos comunes
 */
public class EntityValidator {

    /**
     * Valida si un email tiene formato correcto
     * @param email Email a validar
     * @return true si el email es válido, false en caso contrario
     */
    public static boolean isValidEmail(String email) {
        if (email == null || email.trim().isEmpty()) {
            return false;
        }
        return email.matches("^[A-Za-z0-9+_.-]+@(.+)$");
    }

    /**
     * Valida si una cadena no está vacía
     * @param value Cadena a validar
     * @return true si la cadena no está vacía, false en caso contrario
     */
    public static boolean isNotEmpty(String value) {
        return value != null && !value.trim().isEmpty();
    }

    /**
     * Valida si un precio es válido (mayor a 0)
     * @param price Precio a validar
     * @return true si el precio es válido, false en caso contrario
     */
    public static boolean isValidPrice(double price) {
        return price > 0;
    }

    /**
     * Valida si un stock es válido (mayor o igual a 0)
     * @param stock Stock a validar
     * @return true si el stock es válido, false en caso contrario
     */
    public static boolean isValidStock(int stock) {
        return stock >= 0;
    }

    /**
     * Valida si una cuota es válida (mayor a 0)
     * @param quote Cuota a validar
     * @return true si la cuota es válida, false en caso contrario
     */
    public static boolean isValidQuote(double quote) {
        return quote > 0;
    }

    /**
     * Valida si un ID es válido (mayor a 0)
     * @param id ID a validar
     * @return true si el ID es válido, false en caso contrario
     */
    public static boolean isValidId(int id) {
        return id > 0;
    }

    /**
     * Valida el nombre de un cliente (no vacío y mínimo 2 caracteres)
     * @param name Nombre a validar
     * @return true si el nombre es válido, false en caso contrario
     */
    public static boolean isValidName(String name) {
        return name != null && name.trim().length() >= 2;
    }

    /**
     * Valida el número de teléfono (debe tener 10 dígitos)
     * @param phone Teléfono a validar
     * @return true si el teléfono es válido, false en caso contrario
     */
    public static boolean isValidPhone(String phone) {
        return phone != null && phone.matches("^\\d{10}$");
    }

    /**
     * Valida que la contraseña tenga mínimo 6 caracteres
     * @param password Contraseña a validar
     * @return true si la contraseña es válida, false en caso contrario
     */
    public static boolean isValidPassword(String password) {
        return password != null && password.length() >= 6;
    }

    /**
     * Lanza una excepción si el valor es nulo o vacío
     * @param value Valor a validar
     * @param fieldName Nombre del campo (para el mensaje de error)
     */
    public static void validateNotEmpty(String value, String fieldName) {
        if (value == null || value.trim().isEmpty()) {
            throw new ValidationException("El campo '" + fieldName + "' no puede estar vacío");
        }
    }

    /**
     * Lanza una excepción si el ID no es válido
     * @param id ID a validar
     * @param fieldName Nombre del campo (para el mensaje de error)
     */
    public static void validatePositiveId(int id, String fieldName) {
        if (id <= 0) {
            throw new ValidationException("El " + fieldName + " debe ser un número positivo");
        }
    }

    /**
     * Lanza una excepción si el email no es válido
     * @param email Email a validar
     */
    public static void validateEmailFormat(String email) {
        if (!isValidEmail(email)) {
            throw new ValidationException("El email ingresado no tiene un formato válido");
        }
    }

    /**
     * Lanza una excepción si el precio no es válido
     * @param price Precio a validar
     */
    public static void validatePositivePrice(double price) {
        if (!isValidPrice(price)) {
            throw new ValidationException("El precio debe ser un valor positivo mayor a 0");
        }
    }

    /**
     * Lanza una excepción si el stock no es válido
     * @param stock Stock a validar
     */
    public static void validateNonNegativeStock(int stock) {
        if (!isValidStock(stock)) {
            throw new ValidationException("El stock no puede ser negativo");
        }
    }
}

