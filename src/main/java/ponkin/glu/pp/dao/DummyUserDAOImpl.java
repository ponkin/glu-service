package ponkin.glu.pp.dao;

import lombok.extern.slf4j.Slf4j;
import ponkin.glu.pp.exceptions.UserNotFoundException;
import ponkin.glu.pp.model.User;

import javax.inject.Singleton;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * In-Memory user storage implementation
 * Created at 27.11.2014.
 *
 * @author Alexey Ponkin
 * @version 1
 */
@Singleton
@Slf4j
public class DummyUserDAOImpl implements UserDAO{

    private final Map<String, User> db = new ConcurrentHashMap<String, User>();

    @Override
    public User findById(String userId) throws UserNotFoundException {
        if(userId == null) throw new UserNotFoundException(userId);
        log.debug("Get user {} from DB", userId);
        User user = db.get(userId);
        if(user == null) throw new UserNotFoundException(userId);
        return user;
    }

    @Override
    public void create(User user) {
        log.debug("Save {} to DB", user);
        db.put(user.getId(), user);
    }
}
