package ponkin.glu.pp.exceptions;

/**
 * @author Alexey Ponkin
 * @version 1, 28 Nov 2014
 */
public class UserAlreadyExistsException extends Exception {
    public UserAlreadyExistsException(String userId){
        super(String.format("User with id=%s already exist", userId));
    }
}
