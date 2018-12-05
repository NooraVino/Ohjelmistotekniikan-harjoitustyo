
package nooran.giftwish.dao;

import java.util.List;
import nooran.giftwish.domain.User;

/**
 *
 * @author vino
 */
public interface UserDao {

    User create(User user) throws Exception;

    User findByUsername(String username);

    List<User> getAll();

}
