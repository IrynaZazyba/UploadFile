package by.itech.upload.logic.impl;

import by.itech.upload.logic.UploadFileService;
import by.itech.upload.logic.config.UploadPathParameter;
import by.itech.upload.logic.config.UploadResourceManager;
import by.itech.upload.logic.exception.FileNotFoundUploadServiceException;
import by.itech.upload.logic.exception.UploadServiceException;
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
    private String uploadDirectory;

    public UploadFileServiceImpl(){
        UploadResourceManager resourceManager = UploadResourceManager.getInstance();
        uploadDirectory = resourceManager.getUploadPathValue(UploadPathParameter.UPLOAD_DIRECTORY);
    }

    @Override
    public void uploadFile(String appPath , Part part) throws UploadServiceException, IllegalFileFormatException, IllegalFileSizeException, IllegalFileNameException {

        ValidatorFactory validatorFactory = ValidatorFactory.getInstance();

        FileFormatValidator fileFormatValidator = validatorFactory.getFileFormatValidator();
        FileSizeValidator fileSizeValidator = validatorFactory.getFileSizeValidator();
        FileNameValidator fileNameValidator = validatorFactory.getFileNameValidator();

        String uploadPath = appPath  + File.separator + uploadDirectory;

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
                boolean mkdir = uploadDir.mkdir();
                if (!mkdir) {
                    throw new UploadServiceException("An error occurred while creating the directory");
                }
            }
            part.write(uploadPath + File.separator + fileName);
        } catch (IOException e) {
            logger.log(Level.ERROR, "Error with write file to the directory", e);
            throw new UploadServiceException("Error with write file to the directory " + uploadDirectory, e);
        }

    }

    @Override
    public Set<String> getUploadFileNames(String appPath ) {
        Set<String> uploadFileNames = new HashSet<>();
        String uploadPath = appPath  + File.separator + uploadDirectory;
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
    public File getUploadFile(String name, String appPath ) throws FileNotFoundUploadServiceException {
        String uploadPath = appPath  + File.separator + uploadDirectory + File.separator + name;
        File file = new File(uploadPath);
        if (!file.exists()) {
            throw new FileNotFoundUploadServiceException("Exception in attempt to receive not exists file");
        }
        return file;
    }


}
