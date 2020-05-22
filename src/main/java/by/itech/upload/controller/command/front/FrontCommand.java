package by.itech.upload.controller.command.front;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface FrontCommand {

     void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException;
}
