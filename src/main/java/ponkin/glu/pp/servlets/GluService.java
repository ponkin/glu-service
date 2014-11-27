package ponkin.glu.pp.servlets;

import ponkin.glu.pp.model.Ping;
import ponkin.glu.pp.model.Pong;

/**
 * Created at 27.11.2014.
 *
 * @author Alexey Ponkin
 * @version 1
 */
public interface GluService {

    /**
     * List of all service methods for stat counters
     */
    public static final String PING_METHOD = "PING";

    Pong handler(Ping request);
}
