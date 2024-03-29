package by.pet_project.ens.dao.api;

public class AccessDataException extends RuntimeException{
    public AccessDataException() {
    }

    public AccessDataException(String message) {
        super(message);
    }

    public AccessDataException(String message, Throwable cause) {
        super(message, cause);
    }

    public AccessDataException(Throwable cause) {
        super(cause);
    }

    public AccessDataException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
