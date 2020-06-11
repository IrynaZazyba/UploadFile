package by.itech.upload.controller.command.front.impl;


import by.itech.upload.controller.command.ForwardCommandException;
import by.itech.upload.controller.command.front.FrontCommand;
import by.itech.upload.controller.parameter.JspPageName;
import by.itech.upload.controller.parameter.RequestParameterName;
import by.itech.upload.logic.ServiceFactory;
import by.itech.upload.logic.UploadFileService;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Set;


public class DisplayFile implements FrontCommand {

    private static Logger logger = LogManager.getLogger();


    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        UploadFileService uploadFileService = serviceFactory.getUploadFileService();
        String rootDir = request.getServletContext().getRealPath("");

        Set<String> allUploadFile = uploadFileService.getUploadFileNames(rootDir);
        request.setAttribute(RequestParameterName.ALL_FILE_NAMES, allUploadFile);

        try {
            forwardToPage(request, response, JspPageName.DISPLAY_FILE);
        } catch (ForwardCommandException e) {
            logger.log(Level.ERROR,"Forward to page Exception in DisplayFile command", e);
            response.sendRedirect(JspPageName.ERROR_PAGE);
        }
    }
}
