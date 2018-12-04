/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package giftwish.domain;

import nooran.giftwish.domain.Gift;
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
public class GiftTest {
        
    @Test
    public void equalWhenSameId() {
        Gift gift1 = new Gift(1, null, null, true, null);
        Gift gift2 = new Gift(1, null, null, true, null);
        assertTrue(gift1.equals(gift2));
    }
  
    @Test
    public void notEqualWhenDifferentId() {
        Gift gift1 = new Gift(1, null, null, true, null);
        Gift gift2 = new Gift(55, null, null, true, null);
        assertFalse(gift1.equals(gift2));
    }   
    
    @Test
    public void notEqualWhenDifferentType() {
        Gift gift = new Gift(1, null, null, true, null);
        Object object = new Object();
        assertFalse(gift.equals(object));
    }      
}
    
 

