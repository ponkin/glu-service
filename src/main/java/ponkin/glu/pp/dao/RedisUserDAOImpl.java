package ponkin.glu.pp.dao;

import lombok.extern.slf4j.Slf4j;
import ponkin.glu.pp.exceptions.UserAlreadyExistsException;
import ponkin.glu.pp.model.User;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.inject.Inject;
import javax.inject.Singleton;

/**
 *  User storage Redis key-value storage implementation
 *
 * @author Alexey Ponkin
 * @version 1, 28 Nov 2014
 */
@Singleton
@Slf4j
public class RedisUserDAOImpl implements UserDAO{

    @Inject
    private JedisPool pool;

    @Override
    public User findById(@Nullable String userId) {
        if(userId == null) return null;
        Boolean doesUserExist = Boolean.FALSE;
        Jedis jedis = pool.getResource();
        try{
            doesUserExist = jedis.exists(userId);
        }catch(Exception err){
            log.error("Error while getting user from Redis", err);
        }finally {
            pool.returnResource(jedis);
        }
        if(Boolean.FALSE.equals(doesUserExist)) return null;
        return new User(userId); //TODO: We neeed to use object pool or cache
    }

    @Override
    public void create(@Nonnull User user) throws UserAlreadyExistsException {
        Long result = null;
        Jedis jedis = pool.getResource();
        try{
            result = jedis.setnx(user.getId(), "1");
        }catch(Exception err){
            log.error("Error while creating user in Redis", err);
        }finally {
            pool.returnResource(jedis);
        }
        if(Long.valueOf(0l).equals(result)) throw new UserAlreadyExistsException(user.getId());
    }
}
