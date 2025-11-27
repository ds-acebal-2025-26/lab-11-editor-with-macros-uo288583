package es.uniovi.eii.ds.editor;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import es.uniovi.eii.ds.commands.CommandHandler;
import es.uniovi.eii.ds.commands.UserCommand;

public class TextEditor {
    private StringBuilder text = new StringBuilder();

    /**
     * Inserts words at the end of the text.
     * @param words Words to be inserted.
     */
    public void insert(String[] words) {
        for (String word : words) {
            text.append(" ").append(word);
        }
    }

    /**
     * Deletes the last word from the text.
     */
    public void delete() {
        int indexOfLastWord = text.toString().trim().lastIndexOf(" ");
        if (indexOfLastWord == -1) {
            text = new StringBuilder("");
        } else {
            text.setLength(indexOfLastWord);
        }
    }

    /**
     * Replaces all occurrences of a substring with another substring.
     * @param find Substring to find.
     * @param replace Substring to replace with.
     */
    public void replace(String find, String replace) {
        text = new StringBuilder(text.toString().replace(find, replace));
    }

    /**
     * Gets the current text.
     * @return The current text.
     */
    public String getText() {
        return text.toString();
    }

    /**
     * Sets the text to the specified content.
     * @param content Content to set the text to.
     */
    public void setText(String content) {
        text = new StringBuilder(content);
    }

    /**
     * Displays the help panel with available commands.
     */
    public void help() {
        System.out.println("""
            ┌──────────────────────┬─────────────────────────────────────────────┐
            │ open <file>          │ Open a file                                 │
            │ insert <text>        │ Append text to the end                      │
            │ delete               │ Delete the last word                        │
            │ replace <a> <b>      │ Replace <a> with <b> in the whole document  │
            │ record <a>           │ Start recording a macro <a>                 │
            │ execute <a>          │ Execute the macro <a>                       │
            │ help                 │ Open the help pannel                        │
            └──────────────────────┴─────────────────────────────────────────────┘
        """);
    }

    /**
     * Records a macro by capturing user commands until 'stop' is entered.
     * @param args 
     * @param commandHandler Command handler to retrieve commands
     * @param macros Map to store recorded macros
     */
    public void recordCommand(String[] args, CommandHandler commandHandler, Map<String,UserCommand[]> macros) {
        try {
            if (args.length != 1) {
                throw new IllegalArgumentException("Invalid number of arguments => record <macro_name>");
            }

            String macroName = args[0];
            if (macros.containsKey(macroName)) {
                System.out.println("A macro with the name '" + macroName + "' already exists.");
                return;
            }

            System.out.println("Recording macro '" + macroName + "'. Type 'stop' to finish recording.");

            // Start interactive recording
            List<UserCommand> macroCommands = new ArrayList<>();
            @SuppressWarnings("resource")
            Scanner scanner = new Scanner(System.in);
            while (true) {
                System.out.print("> ");
                String line = scanner.nextLine().trim();

                if (line.equalsIgnoreCase("stop")) {
                    break;
                }

                if (!line.isBlank()) {
                    String[] parts = line.split("\\s+");
                    String commandName = parts[0];
                    String[] commandArgs = new String[parts.length - 1];
                    System.arraycopy(parts, 1, commandArgs, 0, parts.length - 1);

                    UserCommand userCommand = commandHandler.getCommand(commandName);
                    if (userCommand == null) {
                        System.out.println("Unknown command: " + commandName);
                    } else {
                        macroCommands.add(new MacroCommand(userCommand, commandArgs));
                    }
                }
            }

            macros.put(macroName, macroCommands.toArray(new UserCommand[0]));
            System.out.println("Macro '" + macroName + "' recorded successfully.");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }
//Wrapper class to hold command and its arguments  
    private static class MacroCommand implements UserCommand {
        private final UserCommand command;
        private final String[] args;

        public MacroCommand(UserCommand command, String[] args) {
            this.command = command;
            this.args = args;
        }

        @Override
        public void execute(String[] ignoredArgs) {
            command.execute(args);
        }
    }

    /**
     * Executes a recorded macro.
     * @param args Macro name to execute.
     * @param macros List of recorded macros.
     */
    public void executeMacro(String[] args, Map<String,UserCommand[]> macros) {
        if (args.length != 1) {
            System.out.println("Invalid number of arguments => execute <macroName>");
            return;
        }

        String macroName = args[0];
        UserCommand[] macroCommands = macros.get(macroName);

        if (macroCommands == null) {
            System.out.println("Macro '" + macroName + "' does not exist.");
            return;
        }

        for (UserCommand command : macroCommands) {
            command.execute(new String[0]); 
        }
    }
}