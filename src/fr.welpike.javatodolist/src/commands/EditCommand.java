package commands;

import todolist.TodoList;

public class EditCommand extends BaseCommand{

    private TodoList todoList;

    public EditCommand(TodoList todoList) {
        super("Edit", "edit.\\{.*\\}.[0-9]+");
        this.todoList = todoList;
    }

    @Override
    public void run(String args) {
        if(this.verifyArgs(args)) {
            System.out.println("Editing a todo.");

            String[] _args = args.split("[{}]");
            String newTitle = _args[1];
            String _id = _args[_args.length - 1];
            _id = _id.split(" ")[1];
            int id = Integer.parseInt(_id);

            System.out.println("Editing a todo : " + newTitle + "" + id);

            this.todoList.editTodo(id, newTitle);
        } else {
            System.out.println("Error: invalid arguments");
        }
    }
}
