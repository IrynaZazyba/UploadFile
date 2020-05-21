package by.itech.upload.logic.validator.impl;

import by.itech.upload.logic.validator.FileFormat;
import by.itech.upload.logic.validator.FileFormatValidator;

public class FileFormatValidatorImpl implements FileFormatValidator {

    public FileFormatValidatorImpl() {
    }

    @Override
    public boolean validate(String fileFormat) {

        String[] split = fileFormat.split("/");
        try {
            FileFormat.valueOf(split[1].toUpperCase());
            return true;
        } catch (IllegalArgumentException ex) {
            return false;
        }
    }

}
