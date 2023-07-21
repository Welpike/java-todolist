package commands;

import todolist.Todo;
import todolist.TodoList;

import java.util.Scanner;

public class ViewCommand extends BaseCommand {

    private TodoList todoList;

    public ViewCommand(TodoList todoList) {
        super("View", "view.[0-9]+");
        this.todoList = todoList;
    }

    @Override
    public void run(String args) {
        if(this.verifyArgs(args)) {
            System.out.println("View todo.");

            String[] _args = args.split(" ");
            String _id = _args[_args.length - 1];
            int id = Integer.parseInt(_id);
            Scanner scanner = new Scanner(System.in);

            Todo todo = this.todoList.getTodo(id);
            System.out.println("Title: " + todo.getTitle());
        } else {
            System.out.println("Error: invalid arguments");
        }
    }
}
