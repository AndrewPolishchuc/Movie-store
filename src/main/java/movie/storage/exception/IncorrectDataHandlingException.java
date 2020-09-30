package movie.storage.exception;

public class IncorrectDataHandlingException extends RuntimeException {
    public IncorrectDataHandlingException(String message, Throwable ex) {
        super(message, ex);
    }
}
