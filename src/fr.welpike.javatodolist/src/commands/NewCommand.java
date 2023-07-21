package commands;

import todolist.TodoList;

public class NewCommand extends BaseCommand{

    private TodoList todoList;

    public NewCommand(TodoList todoList) {
        super("New", "new.\\{.*\\}");
        this.todoList = todoList;
    }

    @Override
    public void run(String args) {
        if(this.verifyArgs(args)) {
            System.out.println("Adding a todo.");

            String[] _args = args.split("[{}]");

            System.out.println("Creating a todo : " + _args[1]);

            this.todoList.addTodo(_args[1]);
        } else {
            System.out.println("Error: invalid arguments");
        }
    }
}
