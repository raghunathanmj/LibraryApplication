package library.representation;

/**
 * Created by raghunathan.mj on 31/07/15.
 */
public class UserToken {
    private final String userToken;

    public UserToken() {userToken = null;}
    public UserToken(String userToken) {this.userToken = userToken;}

    public String getUserToken() {return userToken;}
}
