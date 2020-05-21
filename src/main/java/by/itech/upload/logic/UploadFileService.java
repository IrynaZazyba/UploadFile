package by.itech.upload.logic;

import by.itech.upload.logic.validator.exception.IllegalFileFormatException;
import by.itech.upload.logic.validator.exception.IllegalFileNameException;
import by.itech.upload.logic.validator.exception.IllegalFileSizeException;

import javax.servlet.http.Part;

public interface UploadFileService {

    String uploadFile(String rootDir, Part part) throws UploadServiceException, IllegalFileFormatException, IllegalFileSizeException, IllegalFileNameException;

}
