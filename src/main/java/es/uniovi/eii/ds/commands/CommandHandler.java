package es.uniovi.eii.ds.commands;

import es.uniovi.eii.ds.editor.TextEditor;
import es.uniovi.eii.ds.filesystem.FileSystem;

public class CommandHandler {
    private final TextEditor editor;
    private final FileSystem fileSystem;

    public CommandHandler(TextEditor editor) {
        this.editor = editor;
        this.fileSystem = new FileSystem();
    }

    public void execute(String command, String[] args) {
        switch (command) {
            case "open" -> open(args);
            case "insert" -> editor.insert(args);
            case "delete" -> editor.delete();
            case "replace" -> replace(args);
            case "help" -> showHelp();
            default -> System.out.println("Unknown command");
        }
        System.out.println(editor.getText());
    }

    private void open(String[] args) {
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

    private void replace(String[] args) {
        if (args.length != 2) {
            System.out.println("Invalid number of arguments => replace <find> <replace>");
            return;
        }
        editor.replace(args[0], args[1]);
    }

    private void showHelp() {
        System.out.println(HELP);
    }

    private static final String HELP = """
            ┌──────────────────────┬─────────────────────────────────────────────┐
            │ open <file>          │                                             │
            │ insert <text>        │ append text to the end                      │
            │ delete               │ delete the last word                        │
            │ replace <a> <b>      │ replace <a> with <b> in the whole document  │
            └──────────────────────┴─────────────────────────────────────────────┘
            """;
}