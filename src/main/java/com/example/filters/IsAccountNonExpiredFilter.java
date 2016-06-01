package com.example.filters;

import com.example.dao.UserRepository;
import com.example.model.UsersModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

//этот класс отслеживает действие логина и записывает в базу время логина
public class IsAccountNonExpiredFilter extends UsernamePasswordAuthenticationFilter {


    public UserRepository getUserRepository() {
        return userRepository;
    }

    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    private UserRepository userRepository;
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException {

        Authentication authentication = super.attemptAuthentication(request, response);

        Date now = new Date();
        UsersModel user = (UsersModel) authentication.getPrincipal();
        UsersModel usersModel = userRepository.findOne(user.getName());
        usersModel.setLoginDate(now);
        userRepository.save(usersModel);

        user.setLoginDate(now);

        return authentication;

    }



}
