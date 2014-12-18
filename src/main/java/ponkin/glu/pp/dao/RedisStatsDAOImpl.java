package ponkin.glu.pp.dao;

import lombok.extern.slf4j.Slf4j;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Statistic DAO Redis implementation
 *
 * @author Alexey Ponkin
 * @version 1, 28 Nov 2014
 */
@Singleton
@Slf4j
public class RedisStatsDAOImpl implements StatsDAO {

    @Inject
    private JedisPool pool;

    @Override
    public void incMethodCall(String userId, String methodName) throws Exception{
        String key = constructKey(userId, methodName);
        Jedis jedis = pool.getResource();
        try {
            jedis.setnx(key, "0");// Set counter to 0 if doesn`t exist
            jedis.incr(key);
        }catch(Exception err){
            log.error("Error while incrementing counter in Redis", err);
            throw err;
        }finally {
            pool.returnResource(jedis);
        }
    }

    @Override
    public Long countMethodCall(String userId, String methodName) throws Exception{
        String key = constructKey(userId, methodName);
        Long result = 0l;
        Jedis jedis = pool.getResource();
        try {
            result = Long.parseLong(jedis.get(key));
        }catch(Exception err){
            log.error("Error while getting counter from Redis", err);
            throw err;
        }finally {
            pool.returnResource(jedis);
        }
        return result;
    }

    private static String constructKey(String userId, String methodName){
        return methodName + "_" + userId;
    }
}
