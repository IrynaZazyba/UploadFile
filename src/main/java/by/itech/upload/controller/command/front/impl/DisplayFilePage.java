package by.itech.upload.controller.command.front.impl;


import by.itech.upload.controller.command.front.FrontCommand;
import by.itech.upload.logic.ServiceFactory;
import by.itech.upload.logic.UploadFileService;
import by.itech.upload.logic.UploadServiceException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Set;


public class DisplayFilePage implements FrontCommand {


    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        UploadFileService uploadFileService = serviceFactory.getUploadFileService();
        String rootDir = request.getServletContext().getRealPath("");

        try {
            Set<String> allUploadFile = uploadFileService.getUploadFileNames(rootDir);
            request.setAttribute("fileNames", allUploadFile);

            //todo проверить есть ли request dispatcher
            request.getRequestDispatcher("WEB-INF/display_file.jsp").forward(request, response);

        } catch (UploadServiceException e) {
            response.sendRedirect("errorPage.jsp");
        }

    }
}
