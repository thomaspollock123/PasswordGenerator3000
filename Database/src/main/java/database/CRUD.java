package database;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.File;
import java.sql.*;

public class CRUD extends InitializeDatabase {

    private static final String db_folder = System.getProperty("user.home") + File.separator + ".PG3000db";
    private static final String db_path = db_folder + File.separator + "PG3000.db";

    private static final String JDBC = "jdbc:sqlite:";

    PasswordObject passwordObject = new PasswordObject();

    public void insertPassword(String owner, String password) {
        String insertSQL = "INSERT INTO Passwords(Owner, password) VALUES(?, ?)";

        try (Connection conn = DriverManager.getConnection(JDBC + db_path);
             PreparedStatement pstmt = conn.prepareStatement(insertSQL)) {

            // Set the parameters for the prepared statement
            pstmt.setString(1, owner);
            pstmt.setString(2, password);
            pstmt.executeUpdate();

            System.out.println("Password inserted successfully!");

        } catch (SQLException e) {
            System.out.println("Database error: " + e.getMessage());
        }
    }

    public ObservableList<PasswordObject> SearchPassword(String owner) {
        ObservableList<PasswordObject> data = FXCollections.observableArrayList();
        String selectSQL = "SELECT Owner, password FROM Passwords WHERE Owner = ?";

        try (Connection conn = DriverManager.getConnection(JDBC + db_path);
             PreparedStatement pstmt = conn.prepareStatement(selectSQL)) {

            pstmt.setString(1, owner);

            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    PasswordObject entry = new PasswordObject(
                            rs.getString("Owner"), rs.getString("password")
                    );
                    data.add(entry);
                }
                System.out.println("Password found!");
            }

        } catch (SQLException e) {
            System.out.println("Database error: " + e.getMessage());
        }
        return data;
    }

    public void UpdatePassword(String owner, String password) {
        String updateSQL = "UPDATE Passwords SET password = ? WHERE Owner = ?";

        try (Connection conn = DriverManager.getConnection(JDBC + db_path);
            PreparedStatement pstmt = conn.prepareStatement(updateSQL)) {

            pstmt.setString(1, password);
            pstmt.setString(2, owner);
            pstmt.executeUpdate();

            System.out.println("Password updated successfully!");
        } catch (SQLException e) {
            System.out.println("Database error: " + e.getMessage());
        }
    }

    public void DeletePassword(String owner) {
        String deleteSQL = "DELETE FROM Passwords WHERE Owner = ?";

        try (Connection conn = DriverManager.getConnection(JDBC + db_path);
             PreparedStatement pstmt = conn.prepareStatement(deleteSQL)) {

            // Set the parameters for the prepared statement
            pstmt.setString(1, owner);
            pstmt.executeUpdate();

            System.out.println("Password deleted successfully!");

        } catch (SQLException e) {
            System.out.println("Database error: " + e.getMessage());
        }
    }

    public ObservableList<PasswordObject> getAllPasswords() {
        ObservableList<PasswordObject> data = FXCollections.observableArrayList();
        String selectSQL = "SELECT id, Owner, password FROM Passwords";

        try (Connection conn = DriverManager.getConnection(JDBC + db_path);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(selectSQL)) {

            while (rs.next()) {
                PasswordObject entry = new PasswordObject(
                        rs.getString("Owner"), rs.getString("password")
                );
                data.add(entry);
            }
        } catch (SQLException e) {
            System.out.println("Database error: " + e.getMessage());
        }
        return data;
    }

    public static void main(String[] args) {
        ;
    }
}
