/*package library.filter;

import javax.ws.rs.container.DynamicFeature;
import javax.ws.rs.container.ResourceInfo;
import javax.ws.rs.core.FeatureContext;
import javax.ws.rs.ext.Provider;

@Provider
public class LogoutFeature implements DynamicFeature {
    private final CurrentUserDAO currentUserDAO;

    public LogoutFeature() {
        this.currentUserDAO = null;
    }

    public LogoutFeature(CurrentUserDAO userDAO) {
        this.currentUserDAO = userDAO;
    }

    public void configure(ResourceInfo resourceInfo, FeatureContext context) {
        if (resourceInfo.getResourceMethod().getAnnotation(Logout.class) != null) {
            System.out.println(resourceInfo.getResourceMethod().getName());
            context.register(new LogoutFilter(this.currentUserDAO));
        }
    }
}*/