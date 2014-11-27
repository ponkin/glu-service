package ponkin.glu.pp;

import com.google.inject.servlet.RequestScoped;
import lombok.Data;
import ponkin.glu.pp.model.User;

/**
 * Context for service to store authenticated user
 *
 * Created at 27.11.2014.
 *
 * @author Alexey Ponkin
 * @version 1
 */
@RequestScoped
@Data
public class ApplicationContextImpl implements ApplicationContext{

    private User user;

}
