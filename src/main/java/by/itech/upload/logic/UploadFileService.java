package by.itech.upload.logic;

import by.itech.upload.bean.UploadFile;
import by.itech.upload.logic.validator.exception.IllegalFileFormatException;
import by.itech.upload.logic.validator.exception.IllegalFileNameException;
import by.itech.upload.logic.validator.exception.IllegalFileSizeException;

import javax.servlet.http.Part;
import java.util.Set;

public interface UploadFileService {

    String uploadFile(String rootDir, Part part) throws UploadServiceException, IllegalFileFormatException, IllegalFileSizeException, IllegalFileNameException;

    Set<Integer> getUploadFilesId() throws UploadServiceException;

    UploadFile getUploadFile(int fileId) throws UploadServiceException;
}
