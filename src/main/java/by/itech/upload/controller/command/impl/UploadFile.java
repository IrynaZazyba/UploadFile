package by.itech.upload.controller.command.impl;

import by.itech.upload.controller.command.Command;
import by.itech.upload.logic.ServiceFactory;
import by.itech.upload.logic.UploadFileService;
import by.itech.upload.logic.UploadServiceException;
import by.itech.upload.logic.validator.exception.IllegalFileFormatException;
import by.itech.upload.logic.validator.exception.IllegalFileSizeException;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class UploadFile implements Command {

    private final static String RESPONSE_PARAMETER_MESSAGE = "message";
    private final static String RESPONSE_MESSAGE_SUCCESS_UPLOAD = "File was successfully upload";
    private final static String RESPONSE_MESSAGE_ILLEGAL_FILE_FORMAT = "Illegal file format";
    private final static String RESPONSE_MESSAGE_ILLEGAL_FILE_SIZE = "Illegal file size";



    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String ajaxResponse = null;
        Map<String, String> answer = new HashMap<>();
        Gson gson = new Gson();

        String realPath = request.getServletContext().getRealPath("");
        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        UploadFileService uploadFileService = serviceFactory.getUploadFileService();

        try {

            String fileLocation = uploadFileService.uploadFile(realPath, request.getPart("img"));

            System.out.println(fileLocation);

        } catch (UploadServiceException e) {
            response.setStatus(200);
            answer.put(RESPONSE_PARAMETER_MESSAGE, RESPONSE_MESSAGE_SUCCESS_UPLOAD);
            ajaxResponse = gson.toJson(answer);
        } catch (IllegalFileFormatException e) {
            response.setStatus(409);
            answer.put(RESPONSE_PARAMETER_MESSAGE, RESPONSE_MESSAGE_ILLEGAL_FILE_FORMAT);
            ajaxResponse = gson.toJson(answer);
        } catch (IllegalFileSizeException e) {
            response.setStatus(409);
            answer.put(RESPONSE_PARAMETER_MESSAGE, RESPONSE_MESSAGE_ILLEGAL_FILE_SIZE);
            ajaxResponse = gson.toJson(answer);
        }

        return ajaxResponse;
    }
}
