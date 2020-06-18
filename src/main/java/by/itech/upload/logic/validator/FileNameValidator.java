package by.itech.upload.logic.validator;


public interface FileNameValidator {

    boolean validateExistsName(String fileName, String uploadPath);

    boolean validateNameForSpecialCharacters(String fileName);
}
