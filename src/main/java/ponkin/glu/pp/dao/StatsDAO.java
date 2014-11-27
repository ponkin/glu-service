package ponkin.glu.pp.dao;

/**
 * Stats/gauge interface.
 * monitor method calls
 *
 * Created at 27.11.2014.
 *
 * @author Alexey Ponkin
 * @version 1
 */
public interface StatsDAO {

    /**
     * Increment method call counter
     *  @param userId
     *  @param methodName
     *  @return new method call counter value
     */
    Long incMethodCall(String userId, String methodName);


}
