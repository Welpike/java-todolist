package commands;

import todolist.Todo;
import todolist.TodoList;

import java.util.Scanner;

public class DeleteCommand extends BaseCommand{

    private TodoList todoList;

    public DeleteCommand(TodoList todoList) {
        super("Delete", "delete.[0-9]+");
        this.todoList = todoList;
    }

    @Override
    public void run(String args) {
        if(this.verifyArgs(args)) {
            System.out.println("Deleting a todo.");

            String[] _args = args.split(" ");
            String _id = _args[_args.length - 1];
            int id = Integer.parseInt(_id);
            Scanner scanner = new Scanner(System.in);

            System.out.print("Are you sure about that (y/n) ? ");
            String input = scanner.nextLine();

            Todo todo = todoList.getTodo(id);

            switch (input) {
                case "y" -> {
                    System.out.println("Deleting todo.");
                    this.todoList.deleteTodo(todo, id);
                }
                case "n" -> System.out.println("Ok, we are not going to delete this todo.");
                default -> System.out.println("Error: unknown token. We not are going to delete this todo.");
            }

        } else {
            System.out.println("Error: invalid arguments");
        }
    }
}
