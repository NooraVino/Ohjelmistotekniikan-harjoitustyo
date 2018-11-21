/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nooran.giftwish.domain;

/**
 *
 * @author vino
 */
public class WishMaker {
    
    public WishMaker() {
        
    }
    
    public boolean makeNewWish(String name) {
        Gift gift = new Gift(name);
        return true;
    }
    
}
