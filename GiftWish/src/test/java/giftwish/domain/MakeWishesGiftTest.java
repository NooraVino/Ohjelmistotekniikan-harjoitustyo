/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package giftwish.domain;

import java.util.List;
import nooran.giftwish.domain.Gift;
import nooran.giftwish.domain.MakeWishes;
import nooran.giftwish.domain.User;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author vino
 */
public class MakeWishesGiftTest {
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
        giftDao.create(new Gift(1, "reppu", "iso ja punainen", false, new User("Noora", "")));
        makeWish = new MakeWishes(userDao, giftDao);
        makeWish.login("Noora", "salasana");
    }
    @Test
    public void listEmpytIfNotLoggedIn() {
        makeWish.logout();
        List<Gift> gifts = makeWish.getUndone();
        
        assertEquals(0, gifts.size());
    } 
     @Test
    public void loggedUsersListContainsAddedGift() {
        addGift("pipo", "musta ja tupsullinen");
        
        List<Gift> gifts = makeWish.getUndone();               
        assertEquals(2, gifts.size());
        Gift gift = gifts.get(1);
        
        assertEquals("pipo", gift.getName());
        assertEquals("Noora", gift.getUser().getUserName());
    }    

    @Test
    public void loggedUsersListDoesNotContainTodosOfOther() {
        addGift("hanskat", "kokoa 10");
        makeWish.logout();
        makeWish.login("Ville", "password");
        
        List<Gift> gifts = makeWish.getUndone();
        assertEquals(0, gifts.size());
    }      
 
    @Test
    public void whenMarkedDoneIsNotListed() {    
        makeWish.markDone(1);
        
        List<Gift> gifts = makeWish.getUndone();               
        assertEquals(0, gifts.size());
    }      
    
    private void addGift(String name, String content) {
        makeWish.makeNewWish(name, content);
    }
}
