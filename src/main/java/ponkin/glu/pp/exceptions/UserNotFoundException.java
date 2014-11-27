package ponkin.glu.pp.exceptions;

/**
 * Created at 27.11.2014.
 *
 * @author Alexey Ponkin
 * @version 1
 */
public class UserNotFoundException extends Exception {
    public UserNotFoundException(String userId){
        super(String.format("User '%s' is not found", userId));
    }
    public UserNotFoundException(){
        this(null);
    }
}
