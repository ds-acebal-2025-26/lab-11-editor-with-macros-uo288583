package es.uniovi.eii.ds.commands;

import es.uniovi.eii.ds.editor.TextEditor;

public class DeleteCommand implements UserCommand {
    private final TextEditor editor;

    public DeleteCommand(TextEditor editor) {
        this.editor = editor;
    }

    @Override
    public void execute(String[] args) {
        editor.delete();
    }
}