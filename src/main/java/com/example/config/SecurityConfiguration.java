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
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
/*
    @Autowired
    private IsAccountNonExpiredFilter authenticationFilter;
*/

        @Autowired
        private UserDetailsService userDetailsService;

    @Autowired
    private UserRepository userRepository;

    @Override
        protected void configure(final HttpSecurity httpSecurity) throws Exception {
            httpSecurity
                    .authorizeRequests()
                    .antMatchers(HttpMethod.GET, "/registration")
                    .permitAll()
                    .antMatchers(HttpMethod.POST, "/registration")
                    .permitAll()
                    .antMatchers("/css/**", "/js/**", "/images/**")
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


    //@Bean
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
/*
    @Bean
    public FilterRegistrationBean filterRegistrationBean () {
        FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        registrationBean.setFilter(authenticationFilter);
        registrationBean.setEnabled(false);
        return registrationBean;
    }
*/
    }


