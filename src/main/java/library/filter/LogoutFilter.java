/*package library.filter;
import library.view.PageView;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.PreMatching;
import javax.ws.rs.core.Cookie;
import javax.ws.rs.core.NewCookie;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;
import java.io.IOException;

@Provider
@PreMatching
public class LogoutFilter implements ContainerRequestFilter {

    private final CurrentUserDAO currentUserDAO;

    public LogoutFilter() {
        this.currentUserDAO = null;
    }

    public  LogoutFilter(CurrentUserDAO userDAO) {
        this.currentUserDAO = userDAO;
    }

    public void filter(ContainerRequestContext requestContext) throws IOException {

        String checkUser = null;
        if (requestContext.getCookies().containsKey("userHeader")) {
            String userHeader = requestContext.getCookies().get("userHeader").getValue();
            if (userHeader != null) {
                checkUser = currentUserDAO.isValidHeader(userHeader);
            }
        }
        if (checkUser == null) {
            requestContext.abortWith(Response.ok(new PageView("access_denied.ftl")).build());
        }
        else {
            String header = requestContext.getCookies().get("userHeader").getValue();
            currentUserDAO.deleteHeader(header);
            Cookie cookie = new Cookie("userHeader", "Logout", "/", "127.0.0.1");
            NewCookie cookies = new NewCookie(cookie, "killer", 0, false);
            requestContext.abortWith(Response.ok(new PageView("logout_done.ftl")).cookie(cookies).build());
        }
    }
}*/