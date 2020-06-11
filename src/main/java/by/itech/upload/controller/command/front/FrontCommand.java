package by.itech.upload.controller.command.front;

import by.itech.upload.controller.command.ForwardCommandException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface FrontCommand {

    void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException;

    default void forwardToPage(HttpServletRequest request, HttpServletResponse response, String path)
            throws ServletException, IOException, ForwardCommandException {
        RequestDispatcher requestDispatcher = request.getRequestDispatcher(path);
        if (requestDispatcher != null) {
            requestDispatcher.forward(request, response);
        } else {
            throw new ForwardCommandException("Exception in forwardToPage() requestDispatcher=null.");
        }
    }
}
