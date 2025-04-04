package database;

import javafx.collections.ObservableList;

import java.io.File;
import java.util.Scanner;
import java.sql.*;

public class CRUD extends InitializeDatabase {

    private static final String db_folder = System.getProperty("user.home") + File.separator + ".PG3000db";
    private static final String db_path = db_folder + File.separator + "PG3000.db";

    private static final String JDBC = "jdbc:sqlite:";

    static Scanner input = new Scanner(System.in);

    private static void insertPassword(String owner, String password) {
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

    private String Read() {
        String selectSQL = "SELECT id, Owner, password FROM Passwords";
        StringBuilder output = new StringBuilder();

        try (Connection conn = DriverManager.getConnection(JDBC + db_path);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(selectSQL)) {

            while (rs.next()) {
                String row = String.format("%-5s%-25s%-10s%n",
                        rs.getInt("id"), rs.getString("Owner"), rs.getString("password"));
                output.append(row);
            }
        } catch (SQLException e) {
            System.out.println("Database error: " + e.getMessage());
        }
        return output.toString();
    }

    public String Update() {
        return "UPDATE Passwords SET Owner = ? , " + "password = ? " + "WHERE id = ?";
    }

    public static void Delete(String owner) {
        String deleteSQL = "DELETE FROM Passwords WHERE Owner = ?";

        try (Connection conn = DriverManager.getConnection(JDBC + db_path);
             PreparedStatement pstmt = conn.prepareStatement(deleteSQL)) {

            // Set the parameters for the prepared statement
            pstmt.setString(1, owner);

            int rowsAffected = pstmt.executeUpdate();

            if (rowsAffected == 0) {
                System.out.println("Owner " + owner + " not found");
            } else {
                System.out.println("Password deleted successfully!");
            }

        } catch (SQLException e) {
            System.out.println("Database error: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        DatabaseSetUp();
        // Delete test
        System.out.println("Enter Password to Delete: ");
        String owner_delete = input.nextLine();
        Delete(owner_delete);

        // Insert test
        System.out.println("Enter Owner Name: ");
        String owner = input.nextLine();
        System.out.print("Enter Password: ");
        String password = input.nextLine();
        // Use prepared statement to safely insert user input into the database
        insertPassword(owner, password);

        input.close();
        // Select test
        CRUD read_instance = new CRUD();
        System.out.println(read_instance.Read());
    }
}
