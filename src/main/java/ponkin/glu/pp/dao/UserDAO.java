package ponkin.glu.pp.dao;

import ponkin.glu.pp.exceptions.UserAlreadyExistsException;
import ponkin.glu.pp.model.User;

/**
 * Created at 27.11.2014.
 *
 * @author Alexey Ponkin
 * @version 1
 */
public interface UserDAO {

    User findById(String userId);

    void create(User user) throws UserAlreadyExistsException;

}
