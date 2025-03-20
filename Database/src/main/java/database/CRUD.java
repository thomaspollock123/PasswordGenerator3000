package database;

import java.io.File;
import java.util.Scanner;
import java.sql.*;

public class CRUD extends InitializeDatabase {

    private static final String db_folder = System.getProperty("user.home") + File.separator + ".PG3000db";
    private static final String db_path = db_folder + File.separator + "PG3000.db";

    static Scanner input = new Scanner(System.in);

    private static void insertPassword(String owner, String password) {
        String insertSQL = "INSERT INTO Passwords(Owner, password) VALUES(?, ?)";

        try (Connection conn = DriverManager.getConnection(db_path);
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

    public static void Create() {
        ;
    }

    public static void Read() {
        ;
    }

    public static void Update() {
        ;
    }

    public static void Delete() {
        ;
    }

    public static void main(String[] args) {
        DatabaseSetUp();
        System.out.println("Enter Owner Name: ");
        String owner = input.nextLine();

        System.out.print("Enter Password: ");
        String password = input.nextLine();

        // Use prepared statement to safely insert user input into the database
        insertPassword(owner, password);

        input.close();
    }
}
