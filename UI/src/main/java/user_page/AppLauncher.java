package user_page;

import database.InitializeDatabase;

public class AppLauncher {

    public static void main(String[] args) {
        InitializeDatabase.DatabaseSetUp();
        // System.out.println(ClassLoader.getSystemResource("Database/PasswordObject.class"));
        JavaFX_Hello.main(args);
    }
}
