/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nooran.giftwish.domain;

import nooran.giftwish.dao.UserDao;

/**
 *
 * @author vino
 */
public class MakeWishes {
     private UserDao userdao;
    
    public MakeWishes(UserDao userdao) {
        this.userdao = userdao;
       
        
    }
    
    public boolean makeNewWish(String name) {
        Gift gift = new Gift(name);
        return true;
    }
    
    public boolean login(String username) {
        User user = userdao.findByUsername(username);
        if (user == null) {
            return false;
        }
        
        //loggedIn = user;
        
        return true;
    }
    
     public boolean createUser(String username, String password)  {   
        if (this.userdao.findByUsername(username) != null) {
            return false;
        }
        User user = new User(username, password);
        try {
            this.userdao.create(user);
        } catch(Exception e) {
            return false;
        }

        return true;
    }
    
}
