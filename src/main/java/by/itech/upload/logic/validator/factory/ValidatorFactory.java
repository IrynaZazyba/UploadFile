package by.itech.upload.logic.validator.factory;

import by.itech.upload.logic.validator.FileFormatValidator;
import by.itech.upload.logic.validator.FileNameValidator;
import by.itech.upload.logic.validator.FileSizeValidator;
import by.itech.upload.logic.validator.impl.FileFormatValidatorImpl;
import by.itech.upload.logic.validator.impl.FileNameValidatorImpl;
import by.itech.upload.logic.validator.impl.FileSizeValidatorImpl;

public class ValidatorFactory {

    private final static ValidatorFactory instance = new ValidatorFactory();
    private final FileFormatValidator fileFormatValidator = new FileFormatValidatorImpl();
    private final FileSizeValidator fileSizeValidator = new FileSizeValidatorImpl();
    private final FileNameValidator fileNameValidator = new FileNameValidatorImpl();

    private ValidatorFactory() {
    }

    public static ValidatorFactory getInstance() {
        return instance;
    }

    public FileNameValidator getFileNameValidator() {
        return fileNameValidator;
    }

    public FileSizeValidator getFileSizeValidator() {
        return fileSizeValidator;
    }

    public FileFormatValidator getFileFormatValidator() {
        return fileFormatValidator;
    }
}
