package by.itech.upload.controller;

import by.itech.upload.controller.command.Command;
import by.itech.upload.controller.command.CommandProvider;
import by.itech.upload.controller.parameter.RequestParameterName;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/upload", name = "FrontController")
public class FrontController extends HttpServlet {

    private static final long serialVersionUID = -8174239011655758543L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doProcess(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doProcess(req, resp);
    }

    public void doProcess(HttpServletRequest req, HttpServletResponse resp) {
        String commandName = req.getParameter(RequestParameterName.COMMAND_NAME);
        CommandProvider commandProvider = CommandProvider.getInstance();
        Command command = commandProvider.getCommand(commandName);
        command.execute(req, resp);

    }
}
