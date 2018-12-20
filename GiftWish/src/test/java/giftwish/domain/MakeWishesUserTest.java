package giftwish.domain;

import java.util.List;
import nooran.giftwish.domain.MakeWishes;
import nooran.giftwish.domain.User;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author vino
 *
 */
public class MakeWishesUserTest {
    FakeGiftDao giftDao;
    FakeUserDao userDao;
    MakeWishes makeWish;

    @Before
    public void setUp() {
        giftDao = new FakeGiftDao();
        userDao = new FakeUserDao();
        User user1 = new User("Noora", "salasana");
        User user2 = new User("Ville", "password");
        userDao.create(user1);
        userDao.create(user2);
        makeWish = new MakeWishes(userDao, giftDao);
        makeWish.login("testaaja1", "salasana");
    }

    @Test
    public void nonExistingUserCanLogIn() {
        boolean result = makeWish.login("ei ole olemassa", "eikätämä");
        assertFalse(result);

        assertEquals(null, makeWish.getLoggedUser());
    }

    @Test
    public void loggedInUserCanLogout() {
        makeWish.login("testaaja2", "salasana");
        makeWish.logout();

        assertEquals(null, makeWish.getLoggedUser());
    }

    @Test
    public void userCreationFailsIfNameNotUnique() throws Exception {
        boolean result = makeWish.createUser("Noora", "salasana");
        assertFalse(result);
    }

    @Test
    public void succesfullyCreatedUserCanLogIn() throws Exception {
        boolean result = makeWish.createUser("testaaja", "salasana");
        assertTrue(result);

        boolean loginOk = makeWish.login("testaaja", "salasana");
        assertTrue(loginOk);

        User loggedIn = makeWish.getLoggedUser();
        assertEquals("testaaja", loggedIn.getUserName());
    }
}
