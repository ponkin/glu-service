package ponkin.glu.pp.aop;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import ponkin.glu.pp.ApplicationContext;
import ponkin.glu.pp.dao.StatsDAO;
import ponkin.glu.pp.model.User;

import javax.inject.Inject;
import javax.inject.Provider;
import javax.inject.Singleton;

/**
 * Interceptor will increment method call counter for user
 *
 * Created at 27.11.2014.
 *
 * @author Alexey Ponkin
 * @version 1
 */
@Singleton
@AllArgsConstructor
@NoArgsConstructor
public class ServiceMethodCountInterceptor implements MethodInterceptor {

    @Inject
    private StatsDAO statsDAO;

    @Inject
    private Provider<ApplicationContext> appCtx;

    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        String methodName = invocation.getMethod().getName();
        User auth = appCtx.get().getUser();
        statsDAO.incMethodCall(auth.getId(), methodName);
        return invocation.proceed();
    }
}
