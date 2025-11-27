package es.uniovi.eii.ds.commands;

import java.util.Map;

import es.uniovi.eii.ds.editor.TextEditor;

public class ExecuteCommand implements UserCommand {
    private final Map<String, UserCommand[]> macros;
    private final TextEditor editor;

    public ExecuteCommand(TextEditor editor, Map<String, UserCommand[]> macros) {
        this.macros = macros;
        this.editor = editor;
    }

    

    @Override
    public void execute(String[] args) {
        editor.executeMacro(args, macros);
    }
}