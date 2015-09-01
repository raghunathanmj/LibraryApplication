package library.resource;

import io.dropwizard.hibernate.UnitOfWork;
import library.dao.UserHibernateDAO;
import library.representation.UserHibernate;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * Created by raghunathan.mj on 19/08/15.
 */
@Path("user-hibernate")
public class UserHibernateResource {

    private final UserHibernateDAO userHibernateDAO;

    public UserHibernateResource() {this.userHibernateDAO = null;}

    public UserHibernateResource(UserHibernateDAO userHibernateDAO) {this.userHibernateDAO = userHibernateDAO;}

    @GET
    @Path("get-user/{username}")
    @UnitOfWork
    @Produces(MediaType.APPLICATION_JSON)
    public UserHibernate getUserByUsername(@PathParam("username")String username) {
        UserHibernate userHibernate = userHibernateDAO.findById(username);
        System.out.println(userHibernate.getName());
        return userHibernate;
    }

    @GET
    @Path("get-all-users")
    @UnitOfWork
    @Produces(MediaType.APPLICATION_JSON)
    public List<UserHibernate> getAllUsers() {
        List<UserHibernate> allUsers = userHibernateDAO.findAll();
        return allUsers;
    }

    @GET
    @Path("create-user/{username}/{password}/{name}")
    @UnitOfWork
    @Produces(MediaType.APPLICATION_JSON)
    public UserHibernate createUser(@PathParam("username") String username, @PathParam("password") String password, @PathParam("name") String name) {
        UserHibernate newUser = new UserHibernate(username, password, name);
        userHibernateDAO.create(newUser);
        return newUser;
    }
}
