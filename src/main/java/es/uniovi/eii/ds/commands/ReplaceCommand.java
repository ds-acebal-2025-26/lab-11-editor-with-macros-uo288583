package es.uniovi.eii.ds.commands;

import es.uniovi.eii.ds.editor.TextEditor;

public class ReplaceCommand implements UserCommand {
    private final TextEditor editor;

    public ReplaceCommand(TextEditor editor) {
        this.editor = editor;
    }

    @Override
    public void execute(String[] args) {
        if (args.length != 2) {
            System.out.println("Invalid number of arguments => replace <find> <replace>");
            return;
        }
        editor.replace(args[0], args[1]);
    }
}