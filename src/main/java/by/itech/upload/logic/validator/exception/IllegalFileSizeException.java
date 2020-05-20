package by.itech.upload.logic.validator.exception;

public class IllegalFileSizeException extends Exception {


    private static final long serialVersionUID = 650198627683915267L;

    public IllegalFileSizeException() {
        super();
    }

    public IllegalFileSizeException(String message) {
        super(message);
    }

    public IllegalFileSizeException(String message, Throwable cause) {
        super(message, cause);
    }

    public IllegalFileSizeException(Throwable cause) {
        super(cause);
    }
}
