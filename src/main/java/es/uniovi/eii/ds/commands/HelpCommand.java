package es.uniovi.eii.ds.commands;

import es.uniovi.eii.ds.editor.TextEditor;

public class HelpCommand implements UserCommand {
    private final TextEditor editor;
    public HelpCommand(TextEditor editor) {
        this.editor = editor;
    }

    @Override
    public void execute(String[] args) {
        editor.help();
    }
}