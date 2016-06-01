package com.example.filters;

import com.example.model.UsersModel;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

@Component
public class CompareSessionFilter  implements Filter {


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Value("${session.timeout.interval}")
    private Integer sessionTimeout;

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        boolean isLoggedOut = false;
        try {
            //показывает текущего пользователя отославшего запрос
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            //этот код проверяет пользователь отославший запрос существует ли в нашей БД(залогиненный)
            if (authentication.getPrincipal() instanceof UsersModel) {

                //этим кодом мы будем вылогинивать пользователя через определенный промежуток времени,
                // если он не проявляет активности на сайте
                //now это время сейчас
                Date now = new Date();
                UsersModel user = (UsersModel) authentication.getPrincipal();
                //если время ожидания превышенно происходит вылогинивание
                System.out.println("Current time: " + now.getTime());
                System.out.println("Logged in time: " + user.getLoginDate().getTime());

                if (user.getLastActive()!=null && (now.getTime()-user.getLastActive().getTime()) > sessionTimeout) {
                    System.out.println("Last updated in time: " + user.getLastActive().getTime());

                    new SecurityContextLogoutHandler().logout((HttpServletRequest)servletRequest,
                            (HttpServletResponse) servletResponse, authentication);
                    ((HttpServletResponse)servletResponse).sendRedirect("/login");
                    isLoggedOut = true;
                } else {
                    user.setLastActive(now);
                }

                if (now.getTime()-user.getLoginDate().getTime()>6000) {
                    new SecurityContextLogoutHandler().logout((HttpServletRequest)servletRequest,
                            (HttpServletResponse) servletResponse, authentication);
                    ((HttpServletResponse)servletResponse).sendRedirect("/login");
                    isLoggedOut = true;
                    System.out.println("Logged out user");
                }
            }

        }
        finally {
            if (!isLoggedOut) {
                filterChain.doFilter(servletRequest, servletResponse);
            }
        }

    }

    @Override
    public void destroy() {

    }
}
