package database;

import todolist.Todo;

import java.io.File;
import java.sql.*;
import java.util.ArrayList;

public class DatabaseManager {
    private String filePath;

    public DatabaseManager(String filePath) {
        this.filePath = System.getProperty("user.dir") + "\\out\\production\\fr.welpike.javatodolist\\" + filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = System.getProperty("user.dir") + "\\out\\production\\fr.welpike.javatodolist\\" + filePath;
    }

    public String getFilePath() {
        return filePath;
    }

    public void createNewDatabase() {

        String url = "jdbc:sqlite:" + this.filePath;

        try {
            Connection conn = DriverManager.getConnection(url);

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public Connection connect() {
        String url = "jdbc:sqlite:" + this.filePath;
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }

    public void createNewTable(String tableName) {
        String url = "jdbc:sqlite:" + this.filePath;

        String sql = "CREATE TABLE IF NOT EXISTS " + tableName + " (\n"
                    + " id integer PRIMARY KEY,\n"
                    + " title text NOT NULL\n"
                    + ");";

        try (
            Connection conn = DriverManager.getConnection(url);
            Statement stmt = conn.createStatement();
        ){
            stmt.execute(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void insert(String tableName, String title) {
        String sql = "INSERT INTO " + tableName + " (title) VALUES(?)";

        try (
            Connection conn = this.connect();
            PreparedStatement pstmt = conn.prepareStatement(sql)
        ) {
            pstmt.setString(1, title);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void edit(String tableName, int id, String newTitle) {
        String sql = "UPDATE " + tableName + " SET title = ? WHERE id = ?";
        try (
            Connection conn = this.connect();
            PreparedStatement pstmt = conn.prepareStatement(sql);
        ){
            pstmt.setString(1, newTitle);
            pstmt.setInt(2, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void delete(String tableName, int id) {
        String sql = "DELETE FROM " + tableName + " WHERE id = ?";
        try (
            Connection conn = this.connect();
            PreparedStatement pstmt = conn.prepareStatement(sql);
        ){
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public ArrayList<Todo> selectAllTodos(){
        String sql = "SELECT * FROM todos";
        ArrayList<Todo> todos = new ArrayList<Todo>();

        try (
            Connection conn = this.connect();
            Statement stmt = conn.createStatement();
        ){
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                Todo newTodo = new Todo(rs.getString("title"));
                todos.add(newTodo);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return todos;
    }

    public Todo selectTodo(int id) {
        String sql = "SELECT * FROM todos WHERE id=?";

        try (
            Connection conn = this.connect();
            PreparedStatement pstmt = conn.prepareStatement(sql);
        ){
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            return new Todo(rs.getString("title"));
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public void setUp() {
        File file = new File(this.filePath);
        if(!file.exists()) {
            this.createNewDatabase();
            this.createNewTable("todos");
        }
    }
}