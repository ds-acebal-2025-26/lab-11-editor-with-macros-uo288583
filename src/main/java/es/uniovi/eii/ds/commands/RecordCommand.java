package es.uniovi.eii.ds.commands;

import java.util.Map;

import es.uniovi.eii.ds.editor.TextEditor;

public class RecordCommand implements UserCommand {
    private final Map<String, UserCommand[]> macros;
    private final CommandHandler commandHandler;
    private final TextEditor editor;
    public RecordCommand(TextEditor editor, Map<String, UserCommand[]> macros, CommandHandler commandHandler) {
        this.macros = macros;
        this.commandHandler = commandHandler;
        this.editor = editor;
    }

    

    @Override
    public void execute(String[] args) {
        editor.recordCommand(args, commandHandler, macros);
    }
}