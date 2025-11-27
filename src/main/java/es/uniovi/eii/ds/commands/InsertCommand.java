package es.uniovi.eii.ds.commands;

import es.uniovi.eii.ds.editor.TextEditor;

public class InsertCommand implements UserCommand {
    private final TextEditor editor;

    public InsertCommand(TextEditor editor) {
        this.editor = editor;
    }

    @Override
    public void execute(String[] args) {
        editor.insert(args);
    }
}