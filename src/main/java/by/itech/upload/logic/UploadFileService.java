package by.itech.upload.logic;

import by.itech.upload.logic.exception.FileNotFoundUploadServiceException;
import by.itech.upload.logic.exception.UploadServiceException;
import by.itech.upload.logic.validator.exception.IllegalFileFormatException;
import by.itech.upload.logic.validator.exception.IllegalFileNameException;
import by.itech.upload.logic.validator.exception.IllegalFileSizeException;

import javax.servlet.http.Part;
import java.io.File;
import java.util.Set;

public interface UploadFileService {

    void uploadFile(String rootDir, Part part) throws UploadServiceException, IllegalFileFormatException, IllegalFileSizeException, IllegalFileNameException;

    Set<String> getUploadFileNames(String rootDir);

    File getUploadFile(String fileName, String rootDir) throws FileNotFoundUploadServiceException;
}
