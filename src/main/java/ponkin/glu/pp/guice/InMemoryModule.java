package ponkin.glu.pp.guice;

import com.google.inject.AbstractModule;
import ponkin.glu.pp.dao.InMemoryStatsDAOImpl;
import ponkin.glu.pp.dao.InMemoryUserDAOImpl;
import ponkin.glu.pp.dao.StatsDAO;
import ponkin.glu.pp.dao.UserDAO;

/**
 * InMemory storage without disk flush
 *
 * @author Alexey Ponkin
 * @version 1, 28 Nov 2014
 */
public class InMemoryModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(StatsDAO.class).to(InMemoryStatsDAOImpl.class).asEagerSingleton();
        bind(UserDAO.class).to(InMemoryUserDAOImpl.class).asEagerSingleton();
    }
}
