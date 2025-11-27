package es.uniovi.eii.ds.commands;

import es.uniovi.eii.ds.editor.TextEditor;
import es.uniovi.eii.ds.filesystem.FileSystem;

import java.util.HashMap;
import java.util.Map;

public class CommandHandler {
    private final Map<String, UserCommand> commandMap = new HashMap<>();
    private final Map<String, UserCommand[]> macros = new HashMap<>();
    private final TextEditor editor;
    public CommandHandler(TextEditor editor) {
        FileSystem fileSystem = new FileSystem();
        this.editor = editor;

        commandMap.put("open", new OpenCommand(editor, fileSystem));
        commandMap.put("insert", new InsertCommand(editor));
        commandMap.put("delete", new DeleteCommand(editor));
        commandMap.put("replace", new ReplaceCommand(editor));
        commandMap.put("help", new HelpCommand());
        commandMap.put("record", new RecordCommand(macros, this));
        commandMap.put("execute", new ExecuteCommand(macros));
    }

    public void operate(String command, String[] args) {
        UserCommand userCommand = commandMap.get(command);
        if (userCommand == null) {
            System.out.println("Unknown command");
            return;
        }
        userCommand.execute(args);
        System.out.println(editor.getText());
    }
    public UserCommand getCommand(String commandName) {
        return commandMap.get(commandName);
    }
}