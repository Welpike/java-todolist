import commands.*;
import todolist.TodoList;

import java.util.Scanner;

public class Parser {
    private TodoList todoList;

    public Parser(TodoList todoList) {
        this.todoList = todoList;
    }

    public boolean parse(boolean loop) {
        NewCommand newCommand = new NewCommand(this.todoList);
        ViewCommand viewCommand = new ViewCommand(this.todoList);
        EditCommand editCommand = new EditCommand(this.todoList);
        DeleteCommand deleteCommand = new DeleteCommand(this.todoList);
        HelpCommand helpCommand = new HelpCommand();

        Scanner scanner = new Scanner(System.in);

        System.out.print(">> ");
        String input = scanner.nextLine();

        String[] _command = input.split(" ");

        switch (_command[0]) {
            case "new" -> newCommand.run(input);
            case "view" -> viewCommand.run(input);
            case "edit" -> editCommand.run(input);
            case "delete" -> deleteCommand.run(input);
            case "help" -> helpCommand.run(input);
            case "exit" -> {
                System.out.println("Bye!");
                loop = false;
                scanner.close();
            }
            default -> System.out.println("Error: unknown command.");
        }

        return loop;
    }
}
