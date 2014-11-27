package ponkin.glu.pp.servlets;



import lombok.extern.slf4j.Slf4j;
import ponkin.glu.pp.model.Ping;
import ponkin.glu.pp.model.Pong;

import javax.inject.Singleton;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.math.BigDecimal;

/**
 * Created at 26.11.2014.
 *
 * @author Alexey Ponkin
 * @version 1
 */
@Singleton
@Path("/handler")
@Slf4j
public class MainFacade {

    @POST
    @Consumes({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
    public Pong handler(Ping request) {
        log.debug("Do post {}");
        return new Pong(BigDecimal.TEN);
    }


}
