package by.itech.upload.logic.impl;

import by.itech.upload.bean.UploadFile;
import by.itech.upload.dao.DAOFactory;
import by.itech.upload.dao.FileInfoDAO;
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


public class UploadFileServiceImpl implements UploadFileService {

    private final DAOFactory daoFactory = DAOFactory.getInstance();
    private final FileInfoDAO fileInfoDAO = daoFactory.getFileInfoDAO();

    private final static Logger logger = LogManager.getLogger();
    private final static String UPLOAD_DIRECTORY = "\\upload";


    @Override
    public String uploadFile(String rootDir, Part part) throws UploadServiceException, IllegalFileFormatException, IllegalFileSizeException, IllegalFileNameException {


        ValidatorFactory validatorFactory = ValidatorFactory.getInstance();

        FileFormatValidator fileFormatValidator = validatorFactory.getFileFormatValidator();
        FileSizeValidator fileSizeValidator = validatorFactory.getFileSizeValidator();
        FileNameValidator fileNameValidator = validatorFactory.getFileNameValidator();

        if (!fileFormatValidator.validate(part.getContentType())) {
            throw new IllegalFileFormatException("Illegal file format");
        }

        if (!fileSizeValidator.validate(part.getSize())) {
            throw new IllegalFileSizeException("File exceeds permissible size");
        }

        String fileName = part.getSubmittedFileName();
        if (!fileNameValidator.validate(fileName,fileInfoDAO)) {
            throw new IllegalFileNameException("File with the same name already exists");
        }


        UploadFile file = new UploadFile();
        file.setTitle(fileName);
        writeFileNameToDB(file);

        String uploadPath = rootDir + File.separator + UPLOAD_DIRECTORY;
        File uploadDir = new File(uploadPath);

        if (!uploadDir.exists()) {
            uploadDir.mkdir();
            //todo если не записалось удалить???
        }


        try {
            part.write(uploadPath + File.separator + fileName);
        } catch (IOException e) {
            logger.log(Level.ERROR, e.getMessage(), e);
            throw new UploadServiceException("Error with write file to the directory " + UPLOAD_DIRECTORY, e);
        }


        return uploadPath + File.separator + fileName;
    }


    private void writeFileNameToDB(UploadFile file) {

        fileInfoDAO.insertFileInfo(file);

    }

}
