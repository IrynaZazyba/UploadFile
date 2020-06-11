package by.itech.upload.controller.command;

import by.itech.upload.controller.command.ajax.AjaxCommand;
import by.itech.upload.controller.command.ajax.AjaxCommandName;
import by.itech.upload.controller.command.ajax.impl.UploadFile;
import by.itech.upload.controller.command.front.FrontCommand;
import by.itech.upload.controller.command.front.FrontCommandName;
import by.itech.upload.controller.command.front.impl.DisplayFile;
import by.itech.upload.controller.command.front.impl.GetFile;

import java.util.HashMap;
import java.util.Map;

public class CommandProvider {

    private final static CommandProvider instance = new CommandProvider();
    private final Map<AjaxCommandName, AjaxCommand> ajaxRepository = new HashMap<>();
    private final Map<FrontCommandName, FrontCommand> frontRepository = new HashMap<>();


    private CommandProvider() {
        ajaxRepository.put(AjaxCommandName.UPLOAD_FILE, new UploadFile());
        frontRepository.put(FrontCommandName.DISPLAY_FILE, new DisplayFile());
        frontRepository.put(FrontCommandName.GET_FILE,new GetFile());

    }

    public static CommandProvider getInstance() {
        return instance;
    }

    public AjaxCommand getAjaxCommand(String name) {
        AjaxCommandName commandName;
        AjaxCommand command;

        if (name == null ) {
            command = ajaxRepository.get(AjaxCommandName.WRONG_REQUEST);
        } else {

            commandName = AjaxCommandName.valueOf(name.toUpperCase());
            command = ajaxRepository.get(commandName);

            if (command == null) {
                command = ajaxRepository.get(AjaxCommandName.WRONG_REQUEST);
            }
        }
        return command;
    }

    public FrontCommand getFrontCommand(String name) {
        FrontCommandName commandName;
        FrontCommand command;

        if (name == null ) {
            command = frontRepository.get(FrontCommandName.WRONG_REQUEST.WRONG_REQUEST);
        } else {

            commandName = FrontCommandName.valueOf(name.toUpperCase());
            command = frontRepository.get(commandName);

            if (command == null) {
                command = frontRepository.get(FrontCommandName.WRONG_REQUEST);
            }
        }
        return command;
    }

}
