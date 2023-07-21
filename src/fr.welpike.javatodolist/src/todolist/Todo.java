package todolist;

public class Todo {
    private String title;

    public Todo(String title) {
        this.title = title;
    }

    public void setTitle(String newTitle) {
        this.title = newTitle;
    }

    public String getTitle() {
        return this.title;
    }
}
