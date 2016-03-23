package fr.iutinfo.skeleton.api;

import fr.iutinfo.skeleton.res.User;
import org.glassfish.jersey.internal.util.Base64;
import org.glassfish.jersey.test.JerseyTest;
import org.junit.Before;
import org.junit.Test;

import javax.ws.rs.core.Application;

import static javax.ws.rs.core.HttpHeaders.AUTHORIZATION;
import static javax.ws.rs.core.Response.Status.UNAUTHORIZED;
import static org.junit.Assert.assertEquals;

public class SecureResourceForAllTest extends JerseyTest {
    private String url = "/secure/forall";
    private Helper h;

    @Override
    protected Application configure() {
        return new Api();
    }

    @Before
    public void init() {
        h = new Helper(target("/userdb"));
        h.initDb();
    }
    @Test
    public void should_return_current_user_with_authorization_header() {
        h.createUserWithPassword("tclavier", "motdepasse", "graindesel");
        String authorization = "Basic " + Base64.encodeAsString("tclavier:motdepasse");
        User utilisateur = target(url).request().header(AUTHORIZATION, authorization).get(User.class);
        assertEquals("tclavier", utilisateur.getName());
    }

    @Test
    public void should_return_annonymous_user_without_authorization_header() {
        User utilisateur = target(url).request().get(User.class);
        assertEquals("Anonymous", utilisateur.getName());
    }

    @Test
    public void should_return_unauthorized_status_for_bad_user() {
        h.createUserWithPassword("tclavier", "motdepasse", "graindesel");
        String authorization = "Basic " + Base64.encodeAsString("tclavier:pasdemotdepasse");
        int utilisateur = target(url).request().header(AUTHORIZATION, authorization).get().getStatus();
        assertEquals(UNAUTHORIZED.getStatusCode(), utilisateur);
    }

}
