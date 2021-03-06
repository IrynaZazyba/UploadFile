package by.itech.upload.controller.command.front.impl;


import by.itech.upload.logic.config.UploadPathParameter;
import by.itech.upload.logic.config.UploadResourceManager;
import by.itech.upload.controller.command.front.FrontCommand;
import by.itech.upload.controller.parameter.RequestParameterName;
import by.itech.upload.logic.exception.FileNotFoundUploadServiceException;
import by.itech.upload.logic.ServiceFactory;
import by.itech.upload.logic.UploadFileService;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;


public class GetFile implements FrontCommand {

    private final static Logger logger = LogManager.getLogger();


    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        String fileName = request.getParameter(RequestParameterName.FILE_NAME);

        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        UploadFileService uploadFileService = serviceFactory.getUploadFileService();
        String appPath  = request.getServletContext().getRealPath("");

        File uploadFile;

        try {
            //getting downloaded file
            uploadFile = uploadFileService.getUploadFile(fileName, appPath );
            String title = uploadFile.getName();

            //getting the directory of downloaded files
            UploadResourceManager resourceManager = UploadResourceManager.getInstance();
            String uploadDirectory = resourceManager.getUploadPathValue(UploadPathParameter.UPLOAD_DIRECTORY);

            //reading and transferring file
            try (InputStream in = request.getServletContext().getResourceAsStream(uploadDirectory + File.separator + title);
                 OutputStream out = response.getOutputStream()) {

                byte[] buffer = in.readAllBytes();
                response.setContentLength(buffer.length);
                response.setContentType(request.getServletContext().getMimeType(title));
                out.write(buffer);
                out.flush();
            }

        } catch (FileNotFoundUploadServiceException e) {
            logger.log(Level.ERROR, "FileNotFoundUploadServiceException in GetFile command method execute()", e);
            response.setStatus(404);
        }
    }
}
