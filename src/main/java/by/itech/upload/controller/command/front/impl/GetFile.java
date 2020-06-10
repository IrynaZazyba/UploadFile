package by.itech.upload.controller.command.front.impl;


import by.itech.upload.controller.command.front.FrontCommand;
import by.itech.upload.logic.ServiceFactory;
import by.itech.upload.logic.UploadFileService;
import by.itech.upload.logic.UploadServiceException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class GetFile implements FrontCommand {

    private final static String UPLOAD_DIRECTORY = "upload\\";
    private final static String REQUEST_PARAMETER_FILE_NAME = "fileName";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        String fileName = request.getParameter(REQUEST_PARAMETER_FILE_NAME);

        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        UploadFileService uploadFileService = serviceFactory.getUploadFileService();

        String rootDir = request.getServletContext().getRealPath("");

        File uploadFile = uploadFileService.getUploadFile(fileName, rootDir);
        if (uploadFile != null) {
            String title = uploadFile.getName();
            try (InputStream in = request.getServletContext().getResourceAsStream(UPLOAD_DIRECTORY + title);
                 OutputStream out = response.getOutputStream()) {

                byte[] buffer = in.readAllBytes();
                response.setContentLength(buffer.length);
                System.out.println(request.getServletContext().getMimeType(title));
                //todo сделать норм
                response.setContentType("image/png");
                out.write(buffer);
                out.flush();
            }
        } else {
            //todo

        }


    }
}
