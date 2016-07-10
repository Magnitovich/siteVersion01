package com.example.config;


import com.example.dao.UserRepository;
import com.example.filters.IsAccountNonExpiredFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private DataSource dataSource;

    @Override
        protected void configure(final HttpSecurity httpSecurity) throws Exception {
            httpSecurity
                    .authorizeRequests()
                    .antMatchers(HttpMethod.GET, "/registration")
                    .permitAll()
                    .antMatchers(HttpMethod.POST, "/registration")
                    .permitAll()
                    .antMatchers("/css/**", "/js/**", "/img/**")
                    .permitAll()
                    .antMatchers("/cars/**")
                    .permitAll()
                    .antMatchers("/yachts/**")
                    .permitAll()
                    .antMatchers("/administrationNotSleeps")
                    .permitAll()
                    .antMatchers("/registrationPage")
                    .permitAll()
                    .antMatchers("/okYouDoIt")
                    .permitAll()
                    .antMatchers("/whisky/**")
                    .permitAll()
                    .antMatchers("/index")
                    .permitAll()
                    .antMatchers("/backgrounds/**")
                    .permitAll()
                    .antMatchers("/name_duplication")
                    .permitAll()
                    .anyRequest().authenticated()
                    .and()
                    .formLogin()
                    .loginPage("/login")
                    .defaultSuccessUrl("/index")
                    .passwordParameter("password")
                    .permitAll()
                    //благодаря этой строчке при logout кидает на индекс, если ее удрать будет кидать на logout
                    .and().logout().logoutSuccessUrl("/index")
                    .and()
                    .httpBasic()
                    .and()
                    .csrf().disable();

                httpSecurity.rememberMe().rememberMeParameter("remember-me")
                .rememberMeCookieName("my-remember-me")
                .tokenRepository(persistentTokenRepository()).tokenValiditySeconds(86400);


            httpSecurity.addFilterBefore(authenticationFilter(), UsernamePasswordAuthenticationFilter.class);
        }

        @Autowired
        public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {

            auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
        }

        @Bean
        public PasswordEncoder passwordEncoder() {
            return new MyPasswordEncoder();
        }


    public IsAccountNonExpiredFilter authenticationFilter() throws Exception {
        IsAccountNonExpiredFilter authFilter = new IsAccountNonExpiredFilter();
        authFilter.setRequiresAuthenticationRequestMatcher(new AntPathRequestMatcher("/login","POST"));

        authFilter.setAuthenticationManager(super.authenticationManager());
        authFilter.setAuthenticationSuccessHandler(new SimpleUrlAuthenticationSuccessHandler("/index"));
        authFilter.setAuthenticationFailureHandler(new SimpleUrlAuthenticationFailureHandler("/login?error"));
        authFilter.setUsernameParameter("username");
        authFilter.setPasswordParameter("password");
        authFilter.setUserRepository(userRepository);
        return authFilter;
    }

    @Bean
    public PersistentTokenRepository persistentTokenRepository() {
        JdbcTokenRepositoryImpl tokenRepository = new JdbcTokenRepositoryImpl();
        tokenRepository.setDataSource(dataSource);
        return tokenRepository;
    }

}


