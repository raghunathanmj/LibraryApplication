package library.representation;

/**
 * Created by raghunathan.mj on 28/07/15.
 */
public class User {
    private final String username;
    private final String password;
    private final String name;

    public User() {
        this.username = "nothing";
        this.password = "nothing";
        this.name = "nothing";
    }

    public User(String username, String password, String name) {
        this.username = username;
        this.password = password;
        this.name = name;
    }

    public String getPassword() {return password;}
    public String getUsername() {return username;}
    public String getName() {return name;}
}
