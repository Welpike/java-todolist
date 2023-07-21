package todolist;

import database.DatabaseManager;

import java.util.ArrayList;

public class TodoList {
    private ArrayList<Todo> todos;
    private DatabaseManager databaseManager = new DatabaseManager("database.db");

    public TodoList() {
    }

    public void setTodos() {
        this.todos = databaseManager.selectAllTodos();
    }

    public ArrayList<Todo> getTodos() {
        return this.todos;
    }

    public void addTodo(String newTodoTitle) {
        Todo newTodo = new Todo(newTodoTitle);
        this.todos.add(newTodo);
        databaseManager.insert("todos", newTodoTitle);
        System.out.println("Todo saved successfully.");
    }

    public Todo getTodo(int todoId) {
        return databaseManager.selectTodo(todoId);
    }

    public void editTodo(int todoId, String newTitle) {
        this.todos.get(todoId).setTitle(newTitle);
        databaseManager.edit("todos", todoId, newTitle);
        System.out.println("Todo edited successfully.");
    }

    public void deleteTodo(Todo todo, int todoId) {
        this.todos.remove(todo);
        databaseManager.delete("todos", todoId);
        System.out.println("Todo deleted successfully.");
    }

    /**
     * to use carefully ;)
     */
    public void clearAllTodos() {
        for(int i = 0; i < this.todos.size(); i++) {
            databaseManager.delete("todos", i);
        }
        this.todos.clear();
    }
}
