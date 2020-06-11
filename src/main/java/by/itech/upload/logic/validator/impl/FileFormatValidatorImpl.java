package by.itech.upload.logic.validator.impl;

import by.itech.upload.logic.validator.FileFormat;
import by.itech.upload.logic.validator.FileFormatValidator;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class FileFormatValidatorImpl implements FileFormatValidator {

    private static Logger logger = LogManager.getLogger();

    public FileFormatValidatorImpl() {
    }

    @Override
    public boolean validate(String fileName) {
        String fileFormat = getFileExtension(fileName);
        try {
            FileFormat.valueOf(fileFormat.toUpperCase());
            return true;
        } catch (IllegalArgumentException ex) {
            logger.log(Level.ERROR,"Illegal argument exception in FileFormatValidatorImpl method validate", ex);
            return false;
        }
    }

    private String getFileExtension(String mystr) {
       return mystr.split("\\.")[1];
    }

}
