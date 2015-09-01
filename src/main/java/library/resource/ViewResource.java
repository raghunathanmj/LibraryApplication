package library.resource;


import com.codahale.metrics.annotation.Timed;
import library.view.PageView;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/")
@Produces(MediaType.TEXT_HTML)
public class ViewResource {

    public ViewResource() {
    }

    /*@GET
    @LoginRequired
    @Path("modify/books/{token}")
    @Timed
    public PageView getBook(@PathParam("token") String token) {
        return new PageView("book-modifier.ftl");
    }*/

    @GET
    @Path("modify/books")
    @Timed
    public PageView getBook() {
        return new PageView("book-modifier.ftl");
    }

    @GET
    @Path("modify/authors")
    @Timed
    public PageView getAuthors() {return new PageView("author_modifier.ftl");}

    @GET
    @Path("withdraw/books")
    @Timed
    public PageView searchByIsbn() {return new PageView("book-withdrawal.ftl");}

    @GET
    @Path("/")
    @Timed
    public PageView login() {return new PageView("sign_inner.ftl");}

    @GET
    @Path("create-user")
    public PageView createNewUser() {return new PageView("user_modifier.ftl");}


    @GET
    @Path("boot")
    public PageView bootExample() {return new PageView("boot.ftl");}

}