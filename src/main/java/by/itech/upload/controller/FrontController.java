package by.itech.upload.controller;

import by.itech.upload.controller.command.CommandProvider;
import by.itech.upload.controller.command.front.FrontCommand;
import by.itech.upload.controller.parameter.RequestParameterName;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/display", name = "FrontController")
public class FrontController extends HttpServlet {

    private static final long serialVersionUID = -3028299814191951878L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doProcess(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doProcess(req, resp);
    }

    private void doProcess(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        String commandName = req.getParameter(RequestParameterName.COMMAND_NAME);
        CommandProvider commandProvider = CommandProvider.getInstance();
        FrontCommand command = commandProvider.getFrontCommand(commandName);
        command.execute(req, resp);


    }
}
