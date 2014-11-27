package ponkin.glu.pp.servlets;



import lombok.extern.slf4j.Slf4j;
import ponkin.glu.pp.ApplicationContext;
import ponkin.glu.pp.aop.Count;
import ponkin.glu.pp.dao.StatsDAO;
import ponkin.glu.pp.model.Ping;
import ponkin.glu.pp.model.Pong;
import ponkin.glu.pp.model.User;

import javax.inject.Inject;
import javax.inject.Provider;
import javax.inject.Singleton;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * JAX-RS Service implementation
 * Created at 26.11.2014.
 *
 * @author Alexey Ponkin
 * @version 1
 */
@Singleton
@Path("/handler")
@Slf4j
public class GluServiceImpl implements GluService {

    @Inject
    private StatsDAO statsDAO;

    @Inject
    private Provider<ApplicationContext> appCtx;

    @POST
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Count
    public Pong handler(Ping request) {
        User auth = appCtx.get().getUser();
        log.debug("Ping method call for user {}", auth);
        return new Pong(statsDAO.countMethodCall(auth.getId(), "handler"));
    }


}
