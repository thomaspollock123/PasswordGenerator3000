package database;

import java.io.File;
import java.sql.*;
import java.util.Set;

public class InitializeDatabase {

    private static final String db_folder = System.getProperty("user.home") + File.separator + ".PG3000db";
    private static final String db_path = db_folder + File.separator + "PG3000.db";

    public static void DatabaseSetUp() {
        File directory = new File(db_folder);
        if (!directory.exists()) {
            directory.mkdirs();
            System.out.println("Database directory created.");
        }

        try (Connection conn = DriverManager.getConnection(getDatabaseUrl())) {
            if (conn != null) {
                DatabaseMetaData meta = conn.getMetaData();
                System.out.println("The driver name is " + meta.getDriverName());
                System.out.println("A new database has been created");
                Statement statement = conn.createStatement(); {
                    statement.execute(createDatabaseTable());
                    // Checks if table "Passwords" exists in database
                    /*ResultSet available_tables = statement.executeQuery("SELECT name FROM sqlite_master WHERE type='table';");
                    while (available_tables.next()) {
                        System.out.println(available_tables.getString("name"));
                    }*/
                }
            }
        } catch (SQLException e) {
            System.out.println("Database initialization error: " + e.getMessage());
        }
    }

    public static String getDatabaseUrl() {
        return "jdbc:sqlite:" + db_path;
    }

    public static String createDatabaseTable() {
        String table = "CREATE TABLE IF NOT EXISTS Passwords ("
                + "    id INTEGER PRIMARY KEY AUTOINCREMENT,"
                + "    Owner text NOT NULL,"
                + "    password text NOT NULL"
                + ");";
        return table;
    }

    public static void main(String[] args) {
        DatabaseSetUp();
        System.out.println("Database URL: " + getDatabaseUrl());
    }
}
