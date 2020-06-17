package by.itech.upload.controller.command.front.impl;


import by.itech.upload.logic.config.UploadPathParameter;
import by.itech.upload.logic.config.UploadResourceManager;
import by.itech.upload.controller.command.front.FrontCommand;
import by.itech.upload.controller.parameter.RequestParameterName;
import by.itech.upload.logic.exception.FileNotFoundUploadServiceException;
import by.itech.upload.logic.ServiceFactory;
import by.itech.upload.logic.UploadFileService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;


public class GetFile implements FrontCommand {


    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        String fileName = request.getParameter(RequestParameterName.FILE_NAME);

        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        UploadFileService uploadFileService = serviceFactory.getUploadFileService();
        String rootDir = request.getServletContext().getRealPath("");

        File uploadFile = null;

        try {
            //получение файла
            uploadFile = uploadFileService.getUploadFile(fileName, rootDir);
            String title = uploadFile.getName();

            //получение директории расположения загруженных файлов
            UploadResourceManager resourceManager = UploadResourceManager.getInstance();
            String uploadDirectory = resourceManager.getUploadPathValue(UploadPathParameter.UPLOAD_DIRECTORY);

            //чтение и передача файла
            try (InputStream in = request.getServletContext().getResourceAsStream(uploadDirectory + File.separator + title);
                 OutputStream out = response.getOutputStream()) {

                byte[] buffer = in.readAllBytes();
                response.setContentLength(buffer.length);
                response.setContentType(request.getServletContext().getMimeType(title));
                out.write(buffer);
                out.flush();
            }

        } catch (FileNotFoundUploadServiceException e) {
            response.setStatus(404);
        }
    }
}
