package ponkin.glu.pp.dao;

import ponkin.glu.pp.exceptions.UserNotFoundException;
import ponkin.glu.pp.model.User;

/**
 * Created at 27.11.2014.
 *
 * @author Alexey Ponkin
 * @version 1
 */
public interface UserDAO {

    User findById(String userId) throws UserNotFoundException;

    void create(User user);

}
