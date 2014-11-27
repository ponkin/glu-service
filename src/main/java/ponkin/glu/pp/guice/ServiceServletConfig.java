package ponkin.glu.pp.guice;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Singleton;
import com.google.inject.servlet.GuiceServletContextListener;
import com.google.inject.servlet.RequestScoped;
import com.google.inject.servlet.ServletModule;
import com.sun.jersey.api.core.PackagesResourceConfig;
import com.sun.jersey.api.core.ResourceConfig;
import com.sun.jersey.guice.spi.container.servlet.GuiceContainer;
import ponkin.glu.pp.ApplicationContext;
import ponkin.glu.pp.ApplicationContextImpl;
import ponkin.glu.pp.dao.DummyStatsDAOImpl;
import ponkin.glu.pp.dao.DummyUserDAOImpl;
import ponkin.glu.pp.dao.StatsDAO;
import ponkin.glu.pp.dao.UserDAO;
import ponkin.glu.pp.filters.Authentication;
import ponkin.glu.pp.servlets.GluService;
import ponkin.glu.pp.servlets.GluServiceImpl;

import java.util.HashMap;
import java.util.Map;

/**
 * Created at 26.11.2014.
 *
 * @author Alexey Ponkin
 * @version 1
 */

public class ServiceServletConfig extends GuiceServletContextListener {
    @Override
    protected Injector getInjector() {
        return Guice.createInjector(new ServletModule() {

            @Override
            protected void configureServlets() {
                bind(StatsDAO.class).to(DummyStatsDAOImpl.class).asEagerSingleton();
                bind(UserDAO.class).to(DummyUserDAOImpl.class).asEagerSingleton();
                bind(ApplicationContext.class).to(ApplicationContextImpl.class).in(RequestScoped.class);
                // services and filters
                bind(Authentication.class).asEagerSingleton();
                bind(GluServiceImpl.class).asEagerSingleton();
                filter("/*").through(Authentication.class);
                serve("/*").with(GuiceContainer.class);
            }

        });
    }
}
