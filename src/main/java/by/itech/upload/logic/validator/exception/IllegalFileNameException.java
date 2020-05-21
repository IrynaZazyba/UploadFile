package by.itech.upload.logic.validator.exception;

public class IllegalFileNameException extends Exception {


    private static final long serialVersionUID = 8917139714286930991L;

    public IllegalFileNameException() {
        super();
    }

    public IllegalFileNameException(String message) {
        super(message);
    }

    public IllegalFileNameException(String message, Throwable cause) {
        super(message, cause);
    }

    public IllegalFileNameException(Throwable cause) {
        super(cause);
    }
}
