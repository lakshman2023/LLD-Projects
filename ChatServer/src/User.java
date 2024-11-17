public class User {
    private String username;
    private boolean isOnline;

    public String getUsername() {
        return username;
    }

    public boolean isOnline() {
        return isOnline;
    }

    public void setOnline(boolean online) {
        isOnline = online;
    }

    public User(String username) {
        this.username = username;
        this.isOnline = false;
    }
}
