package ponkin.glu.pp.dao;

import com.google.inject.Singleton;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ConcurrentHashMap;
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
public class DummyStatsDAOImpl implements StatsDAO{

    private final ConcurrentHashMap<String, AtomicLong> stats = new ConcurrentHashMap<String, AtomicLong>();

    @Override
    public Long incMethodCall(String userId, String methodName) {
        log.debug("Increment method {} call for user {}", methodName, userId);
        String key = constructKey(userId, methodName);
        AtomicLong value = stats.get(key);
        if (value == null) {
            value = stats.putIfAbsent(key, new AtomicLong(1));
            log.debug("Method {} called {} times", methodName, value);
        }
        if (value != null) {
            long l = value.incrementAndGet();
            log.debug("Method {} called {} times", methodName, l);
            return l;
        }else{
            return 1l;
        }
    }

    private static String constructKey(String userId, String methodName){
        return methodName + "_" + userId;
    }
}
