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

    /**
     * Uuden lahjatoiveen luomisesta vastaava metodi
     *
     * @param name lahjatoiveen nimi
     * @param content lahjatoiveen kuvaus
     * @return metodi palauttaa true jos lahjatoiveen luominen onnistuu, muuten
     * false.
     */
    public boolean makeNewWish(String name, String content) {
        Gift gift = new Gift(name, content, loggedIn);
        try {
            giftdao.create(gift);
        } catch (Exception ex) {
            return false;
        }
        return true;

    }
    /**
     * Muokkaa lahjatoivetta
     * 
     * @param id  lahjatoiveen tunniste
     * @param name lahjatoiveen nimi
     * @param content lahjatoiveen sisältö
     */
    public void remakeWish(int id, String name, String content) {
        Gift gift = new Gift(name, content, loggedIn);
        try {
            giftdao.remake(id, name, content);
        } catch (Exception ex) {
        } 
    }

    /**
     * Poistaa lahjatoiveen
     *
     * @param id lahjatoiveen tunniste
     * @see nooran.giftwish.dao.GiftDao#setDone(int)
     */
    public void markDone(int id) {
        try {
            giftdao.setDone(id);
        } catch (Exception ex) {
        }
    }

    /**
     * Listaa lahjatoiveet
     *
     * @return palauttaa kaikki käyttäjän omat lahjatoiveet
     *
     */
    public List<Gift> getUndone() {
        if (loggedIn == null) {
            return new ArrayList<>();
        }

        return giftdao.getAll()
                .stream()
                .filter(t -> t.getUser().equals(loggedIn))
                .filter(t -> !t.isDone())
                .collect(Collectors.toList());
    }
    
 

    /**
     * sisäänkirjautumisesta huolehtiva metodi
     *
     * @param username
     * @return palauttaa true jos käyttäjänimellä löytyy käyttäjä
     * @see nooran.giftwish.dao.UserDao#findByUsername(String)
     */
    public boolean login(String username) {
        User user = userdao.findByUsername(username);
        if (user == null) {
            return false;
        }
        loggedIn = user;
        return true;
    }

    /**
     * kirjaa käyttäjän ulos.
     */
    public void logout() {
        loggedIn = null;
    }
    /**
     * 
     * @return  palautaa kirjautuneena olevan käyttäjän.
     */

    public User getLoggedUser() {
        return loggedIn;
    }

    /**
     *
     * @param username
     * @param password
     * @return Palauttaa true jos uuden käyttäjän luominen onnistuu, muuten
     * palauttaa false.
     *
     * @see nooran.giftwish.dao.UserDao#findByUsername(String)
     * @see nooran.giftwish.dao.UserDao#create(User)
     */
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
