package api.resource;

import api.domain.ActivityEntry;
import api.domain.User;
import api.service.UserService;
import api.utils.PasswordUtils;
import client.ActivityClient;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import javax.crypto.spec.SecretKeySpec;
import javax.inject.Inject;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import java.security.Key;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import static javax.ws.rs.core.HttpHeaders.*;
import static javax.ws.rs.core.Response.Status.*;

@Path("/authorization")
public class AuthenticationResource {

    @Inject
    private UserService userService;
    @Inject
    private ActivityClient activityClient;

    @POST
    @Path("/login")
    public Response authenticateUser(User user) {
        try {
            String login = user.getUsername();
            String password = user.getPassword();
            User loggedUser = userService.authenticate(login, password);
            activityClient.createLoginEntry(new ActivityEntry(loggedUser.getUsername(), loggedUser.getId()));
            String token = issueToken(login, user.getFullName());
            return Response.ok().header(AUTHORIZATION, "Bearer " + token).entity(token).build();
        } catch (Exception e) {
            return Response.status(UNAUTHORIZED).build();
        }
    }

    @POST
    @Path("/register")
    public Response registerUser(User user) {
        String password = PasswordUtils.digestPassword(user.getPassword());
        user.setPassword(password);
        try {
            userService.registerUser(user);
            String login = user.getUsername();
            String token = issueToken(login, user.getFullName());
            return Response.ok().header(AUTHORIZATION, "Bearer " + token).build();
        } catch (Exception e) {
            return Response.status(UNAUTHORIZED).build();
        }
    }


    private String issueToken(String login, String fullName) {
        Key key = generateKey();
        return Jwts
                .builder()
                .setSubject(login)
                .claim("name", fullName)
                .setIssuedAt(new Date())
                .setExpiration(toDate(LocalDateTime.now().plusMinutes(15L)))
                .signWith(SignatureAlgorithm.HS512, key)
                .compact();
    }

    private Date toDate(LocalDateTime localDateTime) {
        return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
    }

    private Key generateKey() {
        String keyString = "secretkey";
        return new SecretKeySpec(keyString.getBytes(), 0, keyString.getBytes().length, "DES");
    }
}
