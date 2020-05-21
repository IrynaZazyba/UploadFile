package by.itech.upload.logic.validator.impl;

import by.itech.upload.bean.UploadFile;
import by.itech.upload.dao.FileInfoDAO;
import by.itech.upload.logic.validator.FileNameValidator;

import java.util.Set;

public class FileNameValidatorImpl implements FileNameValidator {

    private Set<UploadFile> fileNames;

    public FileNameValidatorImpl() {
    }

    @Override
    public boolean validate(String fileName, FileInfoDAO fileInfoDAO) {
        setFileName(fileInfoDAO);

        if (fileNames.size() != 0) {
            for (UploadFile file : fileNames) {
                if (file.getTitle().equals(fileName)) {
                    return false;
                }
            }
        }
        return true;
    }

    private void setFileName(FileInfoDAO fileInfoDAO) {
        this.fileNames = fileInfoDAO.getAllFileName();
    }

}
