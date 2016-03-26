package fr.iutinfo.skeleton.api;

import fr.iutinfo.skeleton.res.model.UserDTO;
import org.glassfish.jersey.test.JerseyTest;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class userDBResourceTest extends JerseyTest {
    private Helper h;

    @Override
    protected Application configure() {
        return new Api();
    }

    @Before
    public void init(){
        h = new Helper(target("/userdb"));
        h.initDb();
    }

    @Test
    @Ignore
    public void read_should_return_a_user_as_object() {
        h.createUserWithName("foo");
        UserDTO utilisateur = target("/userdb/foo").request().get(UserDTO.class);
        assertEquals("foo", utilisateur.getName());
    }

    @Test
    @Ignore
    public void read_user_should_return_good_alias() {
        h.createUserWithAlias("richard stallman", "rms");
        UserDTO user = target("/userdb/richard stallman").request().get(UserDTO.class);
        assertEquals("rms", user.getAlias());
    }

    @Test
    @Ignore
    public void read_user_should_return_good_email() {
        h.createUserWithEmail("Ian Murdock", "ian@debian.org");
        UserDTO user = target("/userdb/Ian Murdock").request().get(UserDTO.class);
        assertEquals("ian@debian.org", user.getEmail());
    }

    @Test
    @Ignore
    public void read_user_should_return_user_with_same_salt() {
        String expectedSalt = "graindesel";
        h.createUserWithPassword("Mark Shuttleworth", "motdepasse", expectedSalt);
        UserDTO user = target("/userdb/Mark Shuttleworth").request().get(UserDTO.class);
        assertEquals(expectedSalt, user.getSalt());
    }

    @Test
    @Ignore
    public void read_user_should_return_hashed_password() throws NoSuchAlgorithmException {
        h.createUserWithPassword("Loïc Dachary", "motdepasse", "grain de sable");
        UserDTO user = target("/userdb/Loïc Dachary").request().get(UserDTO.class);
        assertEquals("5f8619bc1f0e23ef5851cf7070732089", user.getPasswdHash());
    }

    @Test
    @Ignore
    public void create_should_return_the_user_with_valid_id() {
        UserDTO user = new UserDTO(0, "thomas");
        Entity<UserDTO> userEntity = Entity.entity(user, MediaType.APPLICATION_JSON);
        String json = target("/userdb").request().post(userEntity).readEntity(String.class);
        assertEquals("{\"id\":1,\"name\":\"thomas\"", json.substring(0, 23));
    }

    @Test
    @Ignore
    public void list_should_return_all_users() {
        h.createUserWithName("foo");
        h.createUserWithName("bar");
        List<UserDTO> users = target("/userdb/").request().get(new GenericType<List<UserDTO>>() {
        });
        assertEquals(2, users.size());
    }

    @Test
    @Ignore
    public void list_all_must_be_ordered() {
        h.createUserWithName("foo");
        h.createUserWithName("bar");
        List<UserDTO> users = target("/userdb/").request().get(new GenericType<List<UserDTO>>() {
        });
        assertEquals("foo", users.get(0).getName());
    }

}
