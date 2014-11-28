package ponkin.glu.pp.guice;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Provides;
import com.google.inject.matcher.Matchers;
import com.google.inject.servlet.GuiceServletContextListener;
import com.google.inject.servlet.RequestScoped;
import com.google.inject.servlet.ServletModule;
import com.sun.jersey.guice.spi.container.servlet.GuiceContainer;
import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import lombok.extern.slf4j.Slf4j;
import ponkin.glu.pp.ApplicationContext;
import ponkin.glu.pp.ApplicationContextImpl;
import ponkin.glu.pp.aop.ServiceMethodCountInterceptor;
import ponkin.glu.pp.aop.Count;
import ponkin.glu.pp.filters.Authentication;
import ponkin.glu.pp.servlets.GluServiceImpl;
import javax.inject.Singleton;

/**
 * Service configuration for Guice DI
 *
 * Created at 26.11.2014.
 *
 * @author Alexey Ponkin
 * @version 1
 */
@Slf4j
public class ServiceServletConfig extends GuiceServletContextListener {


    @Override
    protected Injector getInjector() {
        return Guice.createInjector(new RedisModule(), new ServletModule() {

            @Override
            protected void configureServlets() {
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

            @Provides
            @Singleton
            Config conf(){
                return ConfigFactory.load().getConfig("service");
            }

        });
    }
}
