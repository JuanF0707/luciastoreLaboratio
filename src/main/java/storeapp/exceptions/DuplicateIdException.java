package storeapp.exceptions;

/**
 * Excepción lanzada cuando se intenta crear una entidad con un ID que ya existe
 */
public class DuplicateIdException extends RuntimeException {
    
    public DuplicateIdException(String message) {
        super(message);
    }
    
    public DuplicateIdException(String message, Throwable cause) {
        super(message, cause);
    }
}

