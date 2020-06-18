package by.itech.upload.logic.validator.impl;

import by.itech.upload.logic.validator.FileNameValidator;

import java.io.File;

/**
 * The FileNameValidatorImpl class implements
 * a file name validator. Excludes duplicate names.
 * Case insensitive
 */
public class FileNameValidatorImpl implements FileNameValidator {


    public FileNameValidatorImpl() {
    }

    @Override
    public boolean validate(String fileName, String uploadPath) {

        File file = new File(uploadPath + File.separator + fileName);
        return !file.exists();
    }

}
