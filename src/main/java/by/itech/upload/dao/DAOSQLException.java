package by.itech.upload.dao;

public class DAOSQLException extends Exception {


    private static final long serialVersionUID = -6510460267015013639L;

    public DAOSQLException() {
        super();
    }

    public DAOSQLException(String message) {
        super(message);
    }

    public DAOSQLException(String message, Throwable cause) {
        super(message, cause);
    }

    public DAOSQLException(Throwable cause) {
        super(cause);
    }
}
