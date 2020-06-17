package by.itech.upload.logic.validator.impl;

import by.itech.upload.logic.validator.FileNameValidator;

import java.io.File;

/**
 * Класс FileNameValidatorImpl реализует валидатор
 * по проверке имени файлов. Исключает повторяющиеся имена.
 * Не чувствителен к регистру.
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
