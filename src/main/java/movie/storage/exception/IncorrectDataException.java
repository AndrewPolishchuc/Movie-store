package movie.storage.exception;

public class IncorrectDataException extends RuntimeException {
    public IncorrectDataException(String message, Throwable ex) {
        super(message, ex);
    }
}
