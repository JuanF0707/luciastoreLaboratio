package storeapp.exceptions;

/**
 * Excepción lanzada cuando se detecta un email duplicado al crear/actualizar una entidad
 */
public class DuplicateEmailException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public DuplicateEmailException() {
        super();
    }

    public DuplicateEmailException(String message) {
        super(message);
    }

    public DuplicateEmailException(String message, Throwable cause) {
        super(message, cause);
    }

    public DuplicateEmailException(Throwable cause) {
        super(cause);
    }
}
