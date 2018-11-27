/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package giftwish.domain;

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
 */
public class UserTest {
    
     @Test
    public void equalWhenSameUsername() {
        User u1 = new User("tester", "Teppo");
        User u2 = new User("tester", "Teppo");
        assertTrue(u1.equals(u2));
    }
    
    @Test
    public void nonEqualWhenDifferentUsername() {
        User u1 = new User("tester", "Teppo");
        User u2 = new User("hellas", "Arto");
        assertFalse(u1.equals(u2));
    } 
    
    @Test
    public void nonEqualWhenDifferentType() {
        User u = new User("tester", "Teppo");
        Object o = new Object();
        assertFalse(u.equals(o));
    }  
}
