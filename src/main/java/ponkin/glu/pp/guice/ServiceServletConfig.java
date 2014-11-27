package ponkin.glu.pp.guice;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.servlet.GuiceServletContextListener;
import com.google.inject.servlet.ServletModule;
import com.sun.jersey.guice.spi.container.servlet.GuiceContainer;
import ponkin.glu.pp.servlets.MainFacade;

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
                bind(MainFacade.class).asEagerSingleton();
                serve("/*").with(GuiceContainer.class);
            }

        });
    }
}
