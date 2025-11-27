package es.uniovi.eii.ds.commands;

public class HelpCommand implements UserCommand {
    @Override
    public void execute(String[] args) {
        System.out.println("""
            ┌──────────────────────┬─────────────────────────────────────────────┐
            │ open <file>          │ Open a file                                 │
            │ insert <text>        │ Append text to the end                      │
            │ delete               │ Delete the last word                        │
            │ replace <a> <b>      │ Replace <a> with <b> in the whole document  │
            └──────────────────────┴─────────────────────────────────────────────┘
        """);
    }
}