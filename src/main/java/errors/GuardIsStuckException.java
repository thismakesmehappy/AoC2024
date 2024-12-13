package errors;

public class GuardIsStuckException extends Exception {

    public GuardIsStuckException() {
        super();
    }

    public GuardIsStuckException(String message) {
        super(message);
    }

    public GuardIsStuckException(String message, Throwable cause) {
        super(message, cause);
    }

}
