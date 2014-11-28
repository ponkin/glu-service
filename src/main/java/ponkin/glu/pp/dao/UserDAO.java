package ponkin.glu.pp.dao;

import ponkin.glu.pp.exceptions.UserAlreadyExistsException;
import ponkin.glu.pp.model.User;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * Created at 27.11.2014.
 *
 * @author Alexey Ponkin
 * @version 1
 */
public interface UserDAO {

    User findById(@Nullable String userId);

    void create(@Nonnull User user) throws UserAlreadyExistsException;

}
