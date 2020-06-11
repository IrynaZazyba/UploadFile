package by.itech.upload.logic.impl;

import by.itech.upload.logic.UploadFileService;
import by.itech.upload.logic.UploadServiceException;
import by.itech.upload.logic.validator.FileFormatValidator;
import by.itech.upload.logic.validator.FileNameValidator;
import by.itech.upload.logic.validator.FileSizeValidator;
import by.itech.upload.logic.validator.exception.IllegalFileFormatException;
import by.itech.upload.logic.validator.exception.IllegalFileNameException;
import by.itech.upload.logic.validator.exception.IllegalFileSizeException;
import by.itech.upload.logic.validator.factory.ValidatorFactory;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;


public class UploadFileServiceImpl implements UploadFileService {

    private final static Logger logger = LogManager.getLogger();
    private final static String UPLOAD_DIRECTORY = "upload";


    @Override
    public String uploadFile(String rootDir, Part part) throws UploadServiceException, IllegalFileFormatException, IllegalFileSizeException, IllegalFileNameException {


        ValidatorFactory validatorFactory = ValidatorFactory.getInstance();

        FileFormatValidator fileFormatValidator = validatorFactory.getFileFormatValidator();
        FileSizeValidator fileSizeValidator = validatorFactory.getFileSizeValidator();
        FileNameValidator fileNameValidator = validatorFactory.getFileNameValidator();

        String uploadPath = rootDir + File.separator + UPLOAD_DIRECTORY;

        if (!fileFormatValidator.validate(part.getSubmittedFileName())) {
            throw new IllegalFileFormatException("Illegal file format");
        }

        if (!fileSizeValidator.validate(part.getSize())) {
            throw new IllegalFileSizeException("File exceeds permissible size");
        }

        String fileName = part.getSubmittedFileName();
        if (!fileNameValidator.validate(fileName, uploadPath)) {
            throw new IllegalFileNameException("File with the same name already exists");
        }


        try {
            File uploadDir = new File(uploadPath);

            if (!uploadDir.exists()) {
                uploadDir.mkdir();
                //todo если не записалось удалить???
            }
            part.write(uploadPath + File.separator + fileName);
        } catch (IOException e) {
            logger.log(Level.ERROR,"Error with write file to the directory", e);
            throw new UploadServiceException("Error with write file to the directory " + UPLOAD_DIRECTORY, e);
        }
        return uploadPath + File.separator + fileName;
    }

    @Override
    public Set<String> getUploadFileNames(String rootDir) {
        Set<String> uploadFileNames = new HashSet<>();
        String uploadPath = rootDir + File.separator + UPLOAD_DIRECTORY;
        File folder = new File(uploadPath);
        File[] files = folder.listFiles();
        if (files != null) {
            for (File file : files) {
                uploadFileNames.add(file.getName());
            }
        }
        return uploadFileNames;
    }

    @Override
    public File getUploadFile(String name, String rootDir) {
        String uploadPath = rootDir + File.separator + UPLOAD_DIRECTORY + File.separator + name;
        File file = new File(uploadPath);
        boolean exists = file.exists();
        if (exists) {
            return file;
        } else {
            return null;
        }
    }


}
