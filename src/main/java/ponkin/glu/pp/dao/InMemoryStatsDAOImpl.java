package ponkin.glu.pp.dao;

import com.google.common.collect.Maps;
import com.google.inject.Singleton;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicLong;

/**
 * In-Memory statistic storage implementation
 * Created at 27.11.2014.
 *
 * @author Alexey Ponkin
 * @version 1
 */
@Singleton
@Slf4j
public class InMemoryStatsDAOImpl implements StatsDAO{

    private final ConcurrentMap<String, AtomicLong> stats = Maps.newConcurrentMap();

    @Override
    public void incMethodCall(String userId, String methodName) {
        log.debug("Increment method {} call for user {}", methodName, userId);
        String key = constructKey(userId, methodName);
        AtomicLong value = stats.get(key);
        if (value == null) {
            value = stats.putIfAbsent(key, new AtomicLong(1));
        }
        if (value != null) {
            value.incrementAndGet();
        }
    }

    @Override
    public Long countMethodCall(String userId, String methodName) {
        String key = constructKey(userId, methodName);
        AtomicLong value = stats.get(key);
        if (value == null) {
            value = stats.putIfAbsent(key, new AtomicLong(0));
        }
        if (value != null) {
            return value.get();
        }else{
            return 0l;
        }
    }

    private static String constructKey(String userId, String methodName){
        return methodName + "_" + userId;
    }
}
