package ponkin.glu.pp.guice;

import com.google.inject.AbstractModule;
import ponkin.glu.pp.dao.RedisStatsDAOImpl;
import ponkin.glu.pp.dao.RedisUserDAOImpl;
import ponkin.glu.pp.dao.StatsDAO;
import ponkin.glu.pp.dao.UserDAO;

/**
 * Module to use with Redis server as storage
 *
 * @author Alexey Ponkin
 * @version 1, 28 Nov 2014
 */
public class RedisModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(StatsDAO.class).to(RedisStatsDAOImpl.class).asEagerSingleton();
        bind(UserDAO.class).to(RedisUserDAOImpl.class).asEagerSingleton();
    }
}
