package by.itech.upload.controller.command;

import by.itech.upload.controller.command.impl.DisplayFile;
import by.itech.upload.controller.command.impl.UploadFile;

import java.util.HashMap;
import java.util.Map;

public class CommandProvider {

    private final static CommandProvider instance = new CommandProvider();
    private final Map<CommandName, Command> repository = new HashMap<>();

    private CommandProvider() {
        repository.put(CommandName.UPLOAD_FILE, new UploadFile());
        repository.put(CommandName.DISPLAY_FILE,new DisplayFile());
    }

    public static CommandProvider getInstance() {
        return instance;
    }

    public Command getCommand(String name) {
        CommandName commandName;
        Command command;

        if (name == null ) {
            command = repository.get(CommandName.WRONG_REQUEST);
        } else {

            commandName = CommandName.valueOf(name.toUpperCase());
            command = repository.get(commandName);

            if (command == null) {
                command = repository.get(CommandName.WRONG_REQUEST);
            }
        }
        return command;
    }

}
