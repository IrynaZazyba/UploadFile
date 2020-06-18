package by.itech.upload.logic.validator.impl;

import by.itech.upload.logic.validator.FileNameValidator;

import java.io.File;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * The FileNameValidatorImpl class implements
 * a file name validator. Excludes duplicate names.
 * Case insensitive
 */
public class FileNameValidatorImpl implements FileNameValidator {

    private  final static String PATTERN_SPECIAL_CHARACTERS ="[!@#$&~%*()\\[\\]{}'\"\\\\:;><`]";

    public FileNameValidatorImpl() {
    }

    @Override
    public boolean validateExistsName(String fileName, String uploadPath) {
        File file = new File(uploadPath + File.separator + fileName);
        return !file.exists();
    }

    @Override
    public boolean validateNameForSpecialCharacters(String fileName){
        Pattern pattern = Pattern.compile(PATTERN_SPECIAL_CHARACTERS);
        Matcher matcher = pattern.matcher(fileName);
        return !matcher.find();
    }


}
