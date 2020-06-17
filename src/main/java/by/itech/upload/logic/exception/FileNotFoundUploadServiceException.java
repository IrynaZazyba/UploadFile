package by.itech.upload.logic.exception;

public class FileNotFoundUploadServiceException extends UploadServiceException {


    private static final long serialVersionUID = -6235480119562697656L;

    public FileNotFoundUploadServiceException() {
    }

    public FileNotFoundUploadServiceException(String message) {
        super(message);
    }

    public FileNotFoundUploadServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public FileNotFoundUploadServiceException(Throwable cause) {
        super(cause);
    }
}
