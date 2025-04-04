package database;

public class PasswordObject {
    private String owner;
    private String password;

    public PasswordObject() {
        this.owner = "";
        this.password = "";
    }

    public PasswordObject(String owner, String password) {
        this.owner = owner;
        this.password = password;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
