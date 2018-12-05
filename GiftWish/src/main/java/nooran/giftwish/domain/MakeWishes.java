/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nooran.giftwish.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import nooran.giftwish.dao.GiftDao;
import nooran.giftwish.dao.UserDao;

/**
 *
 * Sovelluslogiikasta vastaava luokka
 */
public class MakeWishes {

    private User loggedIn;
    private UserDao userdao;
    private GiftDao giftdao;

    public MakeWishes(UserDao userdao, GiftDao giftdao) {
        this.userdao = userdao;
        this.giftdao = giftdao;

    }

    public boolean makeNewWish(String name, String content) {
        Gift gift = new Gift(name, content, loggedIn);
        try {   
           giftdao.create(gift);
        } catch (Exception ex) {
            return false;
        }
        return true;
        
 
    }
    
    public void markDone(int id) {
        try {
            giftdao.setDone(id);
        } catch (Exception ex) {
        }
    }
    
     public List<Gift> getUndone() {
        if (loggedIn == null) {
            return new ArrayList<>();
        }
          
        return giftdao.getAll()
            .stream()
            .filter(t-> t.getUser().equals(loggedIn))
            .filter(t->!t.isDone())
            .collect(Collectors.toList());
    }

    public boolean login(String username) {
        User user = userdao.findByUsername(username);
        if (user == null) {
            return false;
        }
        loggedIn = user;
        return true;
    }

    public void logout() {
        loggedIn = null;
    }

    public User getLoggedUser() {
        return loggedIn;
    }

    public boolean createUser(String username, String password) {
        if (this.userdao.findByUsername(username) != null) {
            return false;
        }
        User user = new User(username, password);
        try {
            this.userdao.create(user);
        } catch (Exception e) {
            return false;
        }

        return true;
    }

}
