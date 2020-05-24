package by.itech.upload.controller.command.front.impl;


import by.itech.upload.bean.UploadFile;
import by.itech.upload.controller.command.front.FrontCommand;
import by.itech.upload.logic.ServiceFactory;
import by.itech.upload.logic.UploadFileService;
import by.itech.upload.logic.UploadServiceException;
import org.apache.logging.log4j.core.util.IOUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class GetFile implements FrontCommand {

    private final static String UPLOAD_DIRECTORY = "upload\\";
    private final static String REQUEST_PARAMETER_FILE_ID="file_id";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        int fileId = Integer.parseInt(request.getParameter(REQUEST_PARAMETER_FILE_ID));

        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        UploadFileService uploadFileService = serviceFactory.getUploadFileService();

        try {
            UploadFile uploadFile = uploadFileService.getUploadFile(fileId);
            if (uploadFile != null) {
                String title = uploadFile.getTitle();
                try (InputStream in = request.getServletContext().getResourceAsStream(UPLOAD_DIRECTORY + title);
                     OutputStream out = response.getOutputStream()) {

                    byte[] buffer = in.readAllBytes();
                    response.setContentLength(buffer.length);
                    System.out.println(request.getServletContext().getMimeType(title));
                    response.setContentType("image/png");
                    out.write(buffer);
                    out.flush();
                }
            } else {

            }
        } catch (UploadServiceException e) {
            response.sendRedirect("errorPage.jsp");
        }


    }
}
