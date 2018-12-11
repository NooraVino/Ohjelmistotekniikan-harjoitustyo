
package nooran.giftwish.dao;

import java.util.List;
import nooran.giftwish.domain.User;

/**
 *
 * Käyttäjien tietojen tallentamisesta vastaava rajapintaluokka
 */
public interface UserDao {

    User create(User user) throws Exception;

    User findByUsername(String username);

    List<User> getAll();

}
