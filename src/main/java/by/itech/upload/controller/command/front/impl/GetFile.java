package by.itech.upload.controller.command.front.impl;


import by.itech.upload.controller.command.front.FrontCommand;
import org.apache.logging.log4j.core.util.IOUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class GetFile implements FrontCommand {



    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        try (InputStream in = request.getServletContext().getResourceAsStream("upload\\timer.png");
             OutputStream out = response.getOutputStream()) {

            byte[] buffer = in.readAllBytes();
            response.setContentLength(buffer.length);
            response.setContentType("image/png");
            out.write(buffer);
            out.flush();
        }

    }
}
