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

    Pong handler(Ping request) throws Exception;
}
