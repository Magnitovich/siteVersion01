package com.example.filters;

import com.example.model.UsersModel;
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

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
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
                if (user.getLastActive()!=null && (now.getTime()-user.getLastActive().getTime())>3000) {

                    new SecurityContextLogoutHandler().logout((HttpServletRequest)servletRequest,
                            (HttpServletResponse) servletResponse, authentication);
                } else {

                    user.setLastActive(now);
                }
            }

        }
        finally {

            filterChain.doFilter(servletRequest, servletResponse);
        }

    }

//    public SecurityContextLogoutHandler loguotFroUI=new SecurityContextLogoutHandler();

    @Override
    public void destroy() {

    }
}
