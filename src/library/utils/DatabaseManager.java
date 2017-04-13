package library.utils;

import library.models.Book;

import java.sql.*;


/**
 * Created by sergey on 13.04.17.
 */
public class DatabaseManager {

    private Connection initConnection() {
        Connection connection = null;
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
             connection = DriverManager.getConnection(
                    "jdbc:postgresql://localhost/library","test_user", "123");
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return connection;
    }

    public void select() {
        Connection connection = initConnection();

        try {
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery("SELECT * FROM book;");
            while (result.next()) {
                System.out.print("Author " + result.getString(2));
                System.out.print(", title " + result.getString("book_title"));
                System.out.print(", year " + result.getInt("book_year"));
                System.out.println(", isbn " + result.getString("book_isbn"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void insert(Book book) {
        Connection connection = initConnection();
        String query = "INSERT INTO book (book_author, book_title, book_isbn, book_year) " +
                " VALUES (?, ?, ?, ?);";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, book.getAuthor());
            preparedStatement.setString(2, book.getTitle());
            preparedStatement.setString(3, book.getIsbn());
            preparedStatement.setInt(4, book.getYear());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(Book book) {
        Connection connection = initConnection();

        String query = "DELETE FROM book WHERE book_isbn=?;";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, book.getIsbn());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void update(Book book) {
        Connection connection = initConnection();
        String query = "UPDATE book SET book_author=?, book_title=?, book_year=? WHERE book_isbn=?";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, book.getAuthor());
            preparedStatement.setString(2, book.getTitle());
            preparedStatement.setInt(3, book.getYear());
            preparedStatement.setString(4, book.getIsbn());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void clearTable() {
        Connection connection = initConnection();

        try {
            Statement statement = connection.createStatement();
            statement.execute("DELETE FROM book;");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
