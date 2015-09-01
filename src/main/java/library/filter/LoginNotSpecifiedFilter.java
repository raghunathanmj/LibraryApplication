package library.filter;
import antlr.StringUtils;
import com.google.api.client.http.HttpTransport;
//import com.google.api.client.http.LowLevelHttpRequest;
//import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.JsonGenerator;
import com.google.api.client.json.JsonParser;
import library.dao.CurrentUserDAO;
import library.dao.UserDAO;
import library.representation.User;
import library.view.PageView;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.PreMatching;
import javax.ws.rs.core.Cookie;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;
import java.io.*;
import java.nio.charset.Charset;
import java.security.GeneralSecurityException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken.Payload;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;

@Provider
@PreMatching
public class LoginNotSpecifiedFilter implements ContainerRequestFilter {

    private final CurrentUserDAO currentUserDAO;

    private final GoogleIdTokenVerifier verifier;

    public LoginNotSpecifiedFilter() {
        this.currentUserDAO = null;
        this.verifier = null;
    }

    public  LoginNotSpecifiedFilter(CurrentUserDAO userDAO) {
        this.currentUserDAO = userDAO;
        this.verifier = new GoogleIdTokenVerifier.Builder(new NetHttpTransport(), new JacksonFactory())
                .setAudience(Arrays.asList("863691724658-7g666g617bn75q24vov9ssuece7m80i1.apps.googleusercontent.com"))
                .build();;
    }

    public void filter(ContainerRequestContext requestContext) throws IOException {

        String[] path = requestContext.getUriInfo().getPath().split("/");

        GoogleIdToken idToken = null;
        try {
            System.out.println(path[path.length - 1]);
            idToken = verifier.verify(path[path.length - 1]);
        } catch (GeneralSecurityException e) {
            e.printStackTrace();
        }
        if (idToken != null) {
            Payload payload = idToken.getPayload();
            System.out.println(payload.getHostedDomain());
            if (payload.getHostedDomain().equals("flipkart.com")) {
                System.out.println("User ID: " + payload.getSubject());
                System.out.println("Email: " + payload.getEmail());
            } else {
                System.out.println("Invalid ID token.");
            }
        } else {
            System.out.println("Invalid ID token.");
        }

    }
}