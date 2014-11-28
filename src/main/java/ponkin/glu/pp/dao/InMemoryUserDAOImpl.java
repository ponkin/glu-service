package ponkin.glu.pp.dao;

import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import ponkin.glu.pp.model.User;

import javax.annotation.Nonnull;
import javax.inject.Singleton;
import java.util.Map;

/**
 * In-Memory user storage implementation
 * Created at 27.11.2014.
 *
 * @author Alexey Ponkin
 * @version 1
 */
@Singleton
@Slf4j
public class InMemoryUserDAOImpl implements UserDAO{

    private final Map<String, User> db = Maps.newConcurrentMap();

    @Override
    public User findById(String userId) {
        log.debug("Get user {} from DB", userId);
        if(userId == null) return null;
        return db.get(userId);
    }

    @Override
    public void create(@Nonnull User user) {
        log.debug("Save {} to DB", user);
        db.put(user.getId(), user);
    }
}
