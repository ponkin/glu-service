package ponkin.glu.pp.servlets;



import lombok.extern.slf4j.Slf4j;
import ponkin.glu.pp.ApplicationContext;
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
    @Consumes({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
    public Pong handler(Ping request) {
        User auth = appCtx.get().getUser();
        log.debug("Ping method call for user {}", auth);
        assert(statsDAO != null);
        return new Pong(statsDAO.incMethodCall(auth.getId(), GluService.PING_METHOD));
    }


}
