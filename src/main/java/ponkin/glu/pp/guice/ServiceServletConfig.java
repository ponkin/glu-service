package ponkin.glu.pp.guice;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.matcher.Matchers;
import com.google.inject.servlet.GuiceServletContextListener;
import com.google.inject.servlet.RequestScoped;
import com.google.inject.servlet.ServletModule;
import com.sun.jersey.guice.spi.container.servlet.GuiceContainer;
import ponkin.glu.pp.ApplicationContext;
import ponkin.glu.pp.ApplicationContextImpl;
import ponkin.glu.pp.aop.ServiceMethodCountInterceptor;
import ponkin.glu.pp.aop.Count;
import ponkin.glu.pp.dao.InMemoryStatsDAOImpl;
import ponkin.glu.pp.dao.InMemoryUserDAOImpl;
import ponkin.glu.pp.dao.StatsDAO;
import ponkin.glu.pp.dao.UserDAO;
import ponkin.glu.pp.filters.Authentication;
import ponkin.glu.pp.servlets.GluServiceImpl;

/**
 * Service configuration for Guice DI
 *
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
                bind(StatsDAO.class).to(InMemoryStatsDAOImpl.class).asEagerSingleton();
                bind(UserDAO.class).to(InMemoryUserDAOImpl.class).asEagerSingleton();
                bind(ApplicationContext.class).to(ApplicationContextImpl.class).in(RequestScoped.class);
                // register aspect to count method calls
                ServiceMethodCountInterceptor interceptor = new ServiceMethodCountInterceptor();
                requestInjection(interceptor);
                bindInterceptor(Matchers.any(), Matchers.annotatedWith(Count.class), interceptor);
                // services and filters
                bind(GluServiceImpl.class).asEagerSingleton();
                filter("/*").through(Authentication.class);
                serve("/*").with(GuiceContainer.class);
            }

        });
    }
}
