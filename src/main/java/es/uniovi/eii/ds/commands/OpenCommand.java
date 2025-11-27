package es.uniovi.eii.ds.commands;

import es.uniovi.eii.ds.editor.TextEditor;
import es.uniovi.eii.ds.filesystem.FileSystem;

public class OpenCommand implements UserCommand {
    private final TextEditor editor;
    private final FileSystem fileSystem;

    public OpenCommand(TextEditor editor, FileSystem fileSystem) {
        this.editor = editor;
        this.fileSystem = fileSystem;
    }

    @Override
    public void execute(String[] args) {
        if (args.length != 1) {
            System.out.println("Invalid number of arguments => open <file>");
            return;
        }
        try {
            String content = fileSystem.readFile(args[0]);
            editor.setText(content);
        } catch (Exception e) {
            System.out.println("Document could not be opened");
        }
    }
}