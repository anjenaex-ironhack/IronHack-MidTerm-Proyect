package com.ironhack.Midtem.Project.security;

import com.ironhack.Midtem.Project.service.impl.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableGlobalMethodSecurity(securedEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private CustomUserDetailsService userDetailsService;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder);

    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.httpBasic();
        http.csrf().disable();
        http.authorizeRequests()
                .antMatchers(HttpMethod.GET, "/accounts").hasRole("ADMIN")
                .antMatchers(HttpMethod.GET, "/accounts/name/**").hasRole("ADMIN")
                .antMatchers(HttpMethod.GET, "/account/id/**").hasRole("ADMIN")
                .antMatchers(HttpMethod.GET, "/account/id/**").hasRole("ACCOUNT_HOLDER")
                .antMatchers(HttpMethod.GET, "/account/balance/id/**").hasRole("ADMIN")
                .antMatchers(HttpMethod.GET, "/account/balance/id/**").hasRole("ACCOUNT_HOLDER")
                .antMatchers(HttpMethod.GET, "/user/account-holders").hasRole("ADMIN")
                .antMatchers(HttpMethod.GET, "/user/account-holder/**").hasRole("ADMIN")
                .antMatchers(HttpMethod.POST, "/create/account-holder").hasRole("ADMIN")
                .antMatchers(HttpMethod.POST, "/create/checking-account").hasRole("ADMIN")
                .antMatchers(HttpMethod.POST, "/create/saving-account").hasRole("ADMIN")
                .antMatchers(HttpMethod.POST, "/create/credit-card").hasRole("ADMIN")
                .antMatchers(HttpMethod.POST, "/account/transfer-money/**").hasRole("ACCOUNT_HOLDER")
                .antMatchers(HttpMethod.PATCH, "/user/third-party/receive-money/{id}").hasRole("ACCOUNT_HOLDER")
                .antMatchers(HttpMethod.PATCH, "/account/balance/**/").hasRole("ADMIN")
                .antMatchers(HttpMethod.PATCH, "/user/third-party/send-money/**").hasRole("THIRD_PARTY")
                .anyRequest().permitAll();
    }
}
