package library.filter;

import io.dropwizard.auth.basic.BasicCredentials;
import library.dao.CurrentUserDAO;
import library.dao.UserDAO;
import library.representation.User;

import javax.jws.soap.SOAPBinding;
import javax.ws.rs.container.DynamicFeature;
import javax.ws.rs.container.ResourceInfo;
import javax.ws.rs.core.FeatureContext;
import javax.ws.rs.ext.Provider;

@Provider
public class LoginRequiredFeature implements DynamicFeature {
    private final CurrentUserDAO currentUserDAO;

    public LoginRequiredFeature() {
        this.currentUserDAO = null;
    }

    public LoginRequiredFeature(CurrentUserDAO userDAO) {
        this.currentUserDAO = userDAO;
    }

    public void configure(ResourceInfo resourceInfo, FeatureContext context) {
        BasicCredentials b;
        if (resourceInfo.getResourceMethod().getAnnotation(LoginRequired.class) != null) {
            System.out.println(resourceInfo.getResourceMethod().getName());
            context.register(new LoginNotSpecifiedFilter(this.currentUserDAO));
        }
    }
}