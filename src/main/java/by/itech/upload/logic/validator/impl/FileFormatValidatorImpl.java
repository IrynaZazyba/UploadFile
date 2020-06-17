package by.itech.upload.logic.validator.impl;

import by.itech.upload.logic.validator.FileFormat;
import by.itech.upload.logic.validator.FileFormatValidator;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Класс FileFormatValidatorImpl реализует валидатор
 * проверки формата загружаемых файлов. Ограничен значениями
 * перечисления FileFormat
 */
public class FileFormatValidatorImpl implements FileFormatValidator {

    private static Logger logger = LogManager.getLogger();
    private final static String REGEX_PATTERN_POINT="\\.";

    public FileFormatValidatorImpl() {
    }

    @Override
    public boolean validate(String fileName) {
        String fileFormat = getFileExtension(fileName);
        try {
            FileFormat.valueOf(fileFormat.toUpperCase());
            return true;
        } catch (IllegalArgumentException ex) {
            logger.log(Level.ERROR, "Illegal argument exception in FileFormatValidatorImpl method validate", ex);
            return false;
        }
    }

    private String getFileExtension(String fileName) {
        String[] splittedFileName = fileName.split(REGEX_PATTERN_POINT);
        return splittedFileName[splittedFileName.length - 1];
    }

}
