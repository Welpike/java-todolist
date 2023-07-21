package commands;

public class HelpCommand extends BaseCommand{
    public HelpCommand() {
        super("Help", "help.\\{.*\\}");
    }

    @Override
    public void run(String args) {
        if(this.verifyArgs(args)) {
            System.out.println("Help");
            String[] argsSplitted = args.split(" ");
            switch (argsSplitted[1]) {
                case "{*}" -> System.out.println("All commands infos");
                case "{NewCommand}" -> System.out.println("NewCommand infos");
                case "{ViewCommand}" -> System.out.println("ViewCommand infos");
                case "{EditCommand}" -> System.out.println("EditCommand infos");
                case "{DeleteCommand}" -> System.out.println("DeleteCommand infos");
                case "{HelpCommand}" -> System.out.println("HelpCommand infos");
                default -> System.out.println("Error: 404, command not found (lol).");
            }
        } else {
            System.out.println("Error: invalid arguments");
        }
    }
}
