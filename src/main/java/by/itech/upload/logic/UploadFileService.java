package by.itech.upload.logic;

import javax.servlet.http.Part;
import java.util.Collection;

public interface UploadFileService {

    String uploadFile(String rootDir, Collection<Part> parts) throws UploadServiceException;

}
