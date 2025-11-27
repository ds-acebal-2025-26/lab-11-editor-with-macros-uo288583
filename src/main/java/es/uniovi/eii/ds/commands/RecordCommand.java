package es.uniovi.eii.ds.commands;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class RecordCommand implements UserCommand {
    private final Map<String, UserCommand[]> macros;
    private final CommandHandler commandHandler;

    public RecordCommand(Map<String, UserCommand[]> macros, CommandHandler commandHandler) {
        this.macros = macros;
        this.commandHandler = commandHandler;
    }

    @Override
    public void execute(String[] args) {
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

    /**
     * A wrapper class to store a UserCommand with its arguments.
     */
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
}