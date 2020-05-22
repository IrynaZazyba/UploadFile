package by.itech.upload.controller.command.front.impl;


import by.itech.upload.controller.command.front.FrontCommand;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class DisplayFilePage implements FrontCommand {

    private final int ARBITARY__SIZE = 2097152;


    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        request.setAttribute("url", "http://localhost:8080/file_upload/display?command=get_file&id=19");
        request.getRequestDispatcher("WEB-INF/display_file.jsp").forward(request,response);
        System.out.println("отдали ссылки");
    }
}
