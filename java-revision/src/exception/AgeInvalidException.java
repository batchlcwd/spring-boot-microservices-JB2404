package exception;

public class AgeInvalidException extends RuntimeException {
    public AgeInvalidException() {
        super("Invalid Age !!");
    }

    public AgeInvalidException(String message) {
        super(message);
    }

}
