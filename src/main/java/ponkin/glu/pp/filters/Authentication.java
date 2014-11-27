package ponkin.glu.pp.filters;

import lombok.extern.slf4j.Slf4j;
import ponkin.glu.pp.ApplicationContext;
import ponkin.glu.pp.dao.UserDAO;
import ponkin.glu.pp.exceptions.UserNotFoundException;
import ponkin.glu.pp.model.User;

import javax.inject.Inject;
import javax.inject.Provider;
import javax.inject.Singleton;
import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

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
        Cookie[] cookies = httpReq.getCookies();
        User user = null;
        String userId = null;
        if (cookies != null)
            for (Cookie cookie : cookies) {
                if (AUTH_COOKIE_NAME.equals(cookie.getName())) {
                    userId = cookie.getValue();
                    break;
                }
            }
        try {
            user = userDAO.findById(userId);
        } catch (UserNotFoundException e) {
            log.debug("Error while fetching user from DB", e);
            userId = java.util.UUID.randomUUID().toString();
            user = new User(userId);
            userDAO.create(user);
            httpRes.addCookie(new Cookie(AUTH_COOKIE_NAME, userId));
        }
        assert (user != null);
        // put user in context
        appCtx.get().setUser(user);
        log.debug("Filter success");
        chain.doFilter(request, response);
    }


}
