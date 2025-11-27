package es.uniovi.eii.ds.commands;

import java.util.Map;

public class ExecuteCommand implements UserCommand {
    private final Map<String, UserCommand[]> macros;

    public ExecuteCommand(Map<String, UserCommand[]> macros) {
        this.macros = macros;
    }

    @Override
    public void execute(String[] args) {
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