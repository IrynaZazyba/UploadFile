package by.itech.upload.logic.validator.impl;

import by.itech.upload.logic.validator.FileSizeValidator;

/**
 * Класс FileSizeValidatorImpl реализует валидатор
 * по проверке размера файлов. Размер ограничен
 * константой FILE_SIZE_LIMIT.
 */
public class FileSizeValidatorImpl implements FileSizeValidator {

    private final static int FILE_SIZE_LIMIT = 2097152;

    public FileSizeValidatorImpl() {
    }

    @Override
    public boolean validate(long fileSize) {
        return fileSize < FILE_SIZE_LIMIT;
    }
}
