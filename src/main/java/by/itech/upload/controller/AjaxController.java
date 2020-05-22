package by.itech.upload.controller;

import by.itech.upload.controller.command.ajax.AjaxCommand;
import by.itech.upload.controller.command.CommandProvider;
import by.itech.upload.controller.parameter.RequestParameterName;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(urlPatterns = "/upload", name = "AjaxController")
@MultipartConfig
public class AjaxController extends HttpServlet {

    private static final long serialVersionUID = -8174239011655758543L;

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
        AjaxCommand command = commandProvider.getAjaxCommand(commandName);


        String jsonAnswer =  command.execute(req, resp);
        PrintWriter out = resp.getWriter();
        //todo filter?
        resp.setContentType("application/json");
        out.print(jsonAnswer);
        out.flush();

    }
}
