package by.itech.upload.logic.validator.exception;

public class IllegalFileFormatException extends Exception {

    private static final long serialVersionUID = -6215099841451379677L;

    public IllegalFileFormatException() {
        super();
    }

    public IllegalFileFormatException(String message) {
        super(message);
    }

    public IllegalFileFormatException(String message, Throwable cause) {
        super(message, cause);
    }

    public IllegalFileFormatException(Throwable cause) {
        super(cause);
    }
}
