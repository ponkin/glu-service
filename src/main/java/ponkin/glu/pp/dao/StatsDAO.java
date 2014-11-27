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
     *
     * @param userId users` id
     * @param methodName method name
     */
    void incMethodCall(String userId, String methodName);

    /**
     * Return method call count for user
     *
     * @param userId users` id
     * @param methodName method name
     * @return method call count for user
     */
    Long countMethodCall(String userId, String methodName);


}
