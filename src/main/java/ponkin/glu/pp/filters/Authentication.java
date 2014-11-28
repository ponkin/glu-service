package ponkin.glu.pp.filters;

import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import ponkin.glu.pp.ApplicationContext;
import ponkin.glu.pp.dao.UserDAO;
import ponkin.glu.pp.exceptions.UserAlreadyExistsException;
import ponkin.glu.pp.model.User;

import javax.inject.Inject;
import javax.inject.Provider;
import javax.inject.Singleton;
import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

/**
 * Assign user generated userId if request
 * doesn`t have special cookie. Otherwise find user by cookie value.
 * <p/>
 * Created at 27.11.2014.
 *
 * @author Alexey Ponkin
 * @version 1
 */
@Singleton
@Slf4j
public class Authentication implements Filter {

    public static final String AUTH_COOKIE_NAME = "USER_ID";

    @Inject
    private UserDAO userDAO;

    @Inject
    private Provider<ApplicationContext> appCtx;

    @Override
    public void destroy() {/* DONOTHING */}

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {/* DONOTHING */}

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        log.debug("Check user identity.");
        HttpServletRequest httpReq = (HttpServletRequest) request;
        HttpServletResponse httpRes = (HttpServletResponse) response;
        Map<String, String> cookiesMap = populateCookies(httpReq.getCookies());
        String userId = cookiesMap.get(AUTH_COOKIE_NAME);

        User user = userDAO.findById(userId);
        if (user == null) {

            userId = java.util.UUID.randomUUID().toString();
            user = new User(userId);
            try {
                userDAO.create(user);
                httpRes.addCookie(new Cookie(AUTH_COOKIE_NAME, userId));
            } catch (UserAlreadyExistsException e) {
                log.error("Id is already in use");
                httpRes.sendError(503, "Service Unavailable");
            }

        }
        // put user in application context
        appCtx.get().setUser(user);
        chain.doFilter(request, response);
    }

    protected static Map<String, String> populateCookies(Cookie[] cookies) {
        Map<String, String> result = Maps.newHashMap();
        if (cookies != null)
            for (Cookie cookie : cookies) {
                result.put(cookie.getName(), cookie.getValue());
            }
        return result;
    }

}
