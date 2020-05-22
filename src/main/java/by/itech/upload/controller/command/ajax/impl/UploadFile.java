package by.itech.upload.controller.command.ajax.impl;

import by.itech.upload.controller.command.ajax.AjaxCommand;
import by.itech.upload.logic.ServiceFactory;
import by.itech.upload.logic.UploadFileService;
import by.itech.upload.logic.UploadServiceException;
import by.itech.upload.logic.validator.exception.IllegalFileFormatException;
import by.itech.upload.logic.validator.exception.IllegalFileNameException;
import by.itech.upload.logic.validator.exception.IllegalFileSizeException;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class UploadFile implements AjaxCommand {

    private final static String RESPONSE_PARAMETER_MESSAGE = "message";
    private final static String RESPONSE_MESSAGE_SUCCESS_UPLOAD = "File was successfully upload";
    private final static String RESPONSE_MESSAGE_ILLEGAL_FILE_FORMAT = "Illegal file format";
    private final static String RESPONSE_MESSAGE_ILLEGAL_FILE_SIZE = "Illegal file size";
    private final static String RESPONSE_MESSAGE_ILLEGAL_FILE_NAME = "File with the same name already exists";
    private final static String REQUEST_FILE_PARAMETER = "img";


    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String ajaxResponse = null;
        Map<String, String> answer = new HashMap<>();
        Gson gson = new Gson();

        String realPath = request.getServletContext().getRealPath("");
        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        UploadFileService uploadFileService = serviceFactory.getUploadFileService();

        try {

            Part file = request.getPart(REQUEST_FILE_PARAMETER);

            if (file != null) {
                String fileLocation = uploadFileService.uploadFile(realPath, file);
                System.out.println(fileLocation);
            } else {


            }

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
        } catch (IllegalFileNameException e) {
            response.setStatus(409);
            answer.put(RESPONSE_PARAMETER_MESSAGE, RESPONSE_MESSAGE_ILLEGAL_FILE_NAME);
            ajaxResponse = gson.toJson(answer);        }

        return ajaxResponse;
    }
}