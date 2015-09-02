package library.resource;

import io.dropwizard.hibernate.UnitOfWork;
import library.dao.UserDAO;
import library.representation.User;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * Created by raghunathan.mj on 19/08/15.
 */
@Path("user")
public class UserResource {

    private final UserDAO userDAO;

    public UserResource() {this.userDAO = null;}

    public UserResource(UserDAO userDAO) {this.userDAO = userDAO;}

    @GET
    @Path("get/{username}")
    @UnitOfWork
    @Produces(MediaType.APPLICATION_JSON)
    public User getUserById(@PathParam("username")String username) {
        User user = userDAO.findById(username);
        System.out.println(user.getName());
        return user;
    }

    @GET
    @Path("get-all")
    @UnitOfWork
    @Produces(MediaType.APPLICATION_JSON)
    public List<User> getAllUsers() {
        List<User> allUsers = userDAO.findAll();
        return allUsers;
    }

    @POST
    @Path("create/{username}/{password}/{name}/{email}")
    @UnitOfWork
    @Produces(MediaType.APPLICATION_JSON)
    public User createUser(@PathParam("username") String username,
                           @PathParam("password") String password,
                           @PathParam("name") String name,
                           @PathParam("email") String email) {
        User newUser = new User(username, password, name, email);
        userDAO.create(newUser);
        return newUser;
    }
}
