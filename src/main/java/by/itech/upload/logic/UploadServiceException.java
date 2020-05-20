package by.itech.upload.logic;

public class UploadServiceException extends Exception {


    private static final long serialVersionUID = -9137688360429300451L;

    public UploadServiceException() {
    }

    public UploadServiceException(String message) {
        super(message);
    }

    public UploadServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public UploadServiceException(Throwable cause) {
        super(cause);
    }
}
