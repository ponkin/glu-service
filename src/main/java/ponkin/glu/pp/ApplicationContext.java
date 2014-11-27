package ponkin.glu.pp;

import ponkin.glu.pp.model.User;

/**
 * Store session data
 *
 * Created at 27.11.2014.
 *
 * @author Alexey Ponkin
 * @version 1
 */
public interface ApplicationContext {

    void setUser(User user);

    User getUser();
}
