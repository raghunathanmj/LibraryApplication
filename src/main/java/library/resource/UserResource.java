package library.resource;

import io.dropwizard.auth.basic.BasicCredentials;
import library.dao.AuthorBookRelationDAO;
import library.dao.CurrentUserDAO;
import library.dao.LibraryDAO;
import library.dao.UserDAO;
import library.filter.LoginRequired;
import library.filter.Logout;
import library.representation.User;
import library.representation.UserToken;
import library.view.PageView;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.*;
import java.net.URI;
import java.util.List;

/**
 * Created by raghunathan.mj on 30/07/15.
 */

@Path("/user")
public class UserResource {
    private final UserDAO userDAO;
    private final CurrentUserDAO currentUserDAO;

    public UserResource(UserDAO userDAO, CurrentUserDAO currentUserDAO) {
        this.userDAO = userDAO;
        this.currentUserDAO = currentUserDAO;
    }

    @POST
    @Path("login")
    @Produces(MediaType.APPLICATION_JSON)
    public Response signIn(User userAttempt){
        User userCheck = userDAO.getUserByUsername(userAttempt.getUsername());
        if (userCheck != null && userCheck.getPassword().equals(userAttempt.getPassword())) {
            String userHeader = userCheck.getUsername() + userCheck.getName();//This needs to be changed
            String presentUser = currentUserDAO.isLoggedIn(userCheck.getUsername());
            if (presentUser == null)
                currentUserDAO.createCurrentUser(userHeader, userAttempt.getUsername());
            Cookie cookie = new Cookie("userHeader", userHeader, "/", "127.0.0.1");
            NewCookie cookies = new NewCookie(cookie);
            return Response.ok(new UserToken("/modify/books")).cookie(cookies).build();
        }
        else
            return Response.ok(new UserToken("denied")).build();
    }

    @POST
    @Path("create")
    @Produces(MediaType.APPLICATION_JSON)
    public Response createUser(User userNew) {
        User isPresent = userDAO.getUserByUsername(userNew.getUsername());
        if (isPresent == null) {
            userDAO.createUser(userNew.getUsername(), userNew.getPassword(), userNew.getName());
            return Response.ok(1).build();
        }
        else
            return Response.ok(0).build();
    }

    @GET
    @Logout
    @Path("logout")
    public void logOutUser() {} //Filter does all the logout required :P
}
