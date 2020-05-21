package by.itech.upload.logic.validator;


import by.itech.upload.dao.FileInfoDAO;

public interface FileNameValidator {

    boolean validate(String fileName, FileInfoDAO fileInfoDAO);
}
