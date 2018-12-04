package giftwish.domain;

import java.util.ArrayList;
import java.util.List;
import nooran.giftwish.dao.UserDao;
import nooran.giftwish.domain.User;

/**
 *
 * @author vino
 */
public class FakeUserDao implements UserDao {

    List<User> users = new ArrayList<>();

    public FakeUserDao() {
        users.add(new User("Kalle", "kissa22"));
    }

    @Override
    public User findByUsername(String username) {
        return users.stream().filter(u -> u.getUserName().equals(username)).findFirst().orElse(null);
    }

    @Override
    public User create(User user) {
        users.add(user);
        return user;
    }

    @Override
    public List<User> getAll() {
        return users;
    }

}
