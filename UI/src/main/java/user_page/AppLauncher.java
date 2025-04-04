package user_page;

import database.InitializeDatabase;

public class AppLauncher {

    public static void main(String[] args) {
        InitializeDatabase.DatabaseSetUp();
        JavaFX_Hello.main(args);
    }
}
