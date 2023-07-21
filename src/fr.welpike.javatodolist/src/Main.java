import database.DatabaseManager;
import todolist.Todo;
import todolist.TodoList;

public class Main {
    public static void main(String[] args) {
        DatabaseManager databaseManager = new DatabaseManager("database.db");
        databaseManager.setUp();

        TodoList todoList = new TodoList();
        todoList.setTodos();

        Parser parser = new Parser(todoList);

        welcome();
        loading();

        for(Todo todo : todoList.getTodos()) {
            System.out.println(todo.getTitle());
        }

        boolean loop = true;

        while (loop) {
            loop = parser.parse(loop);
        }
    }

    public static void loading() {
        Thread loadingThread = new Thread(() -> {
            String[] loadingText = { "Loading |", "Loading /", "Loading -", "Loading \\" };
            int index = 0;

            while (!Thread.currentThread().isInterrupted()) {
                System.out.print("\r" + loadingText[index]);
                index = (index + 1) % loadingText.length;

                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        });

        loadingThread.start();

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        loadingThread.interrupt();

        System.out.println("\r" + " ");  // clear loading messages
    }

    public static void welcome() {
        System.out.println("    _                         _            _       _ _     _           \n" +
                           "   (_)                       | |          | |     | (_)   | |          \n" +
                           "    _  __ ___   ____ _ ______| |_ ___   __| | ___ | |_ ___| |_         \n" +
                           "   | |/ _` \\ \\ / / _` |______| __/ _ \\ / _` |/ _ \\| | / __| __|    \n" +
                           "   | | (_| |\\ V / (_| |      | || (_) | (_| | (_) | | \\__ \\ |_      \n" +
                           "   | |\\__,_| \\_/ \\__,_|       \\__\\___/ \\__,_|\\___/|_|_|___/\\__|\n" +
                           "  _/ |                                                                 \n" +
                           " |__/                                                           by Welpike");
    }
}