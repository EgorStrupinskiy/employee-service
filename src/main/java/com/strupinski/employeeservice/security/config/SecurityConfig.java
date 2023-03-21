package com.strupinski.employeeservice.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    DataSource dataSource;

    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication().dataSource(dataSource);
//                User.UserBuilder userBuilder = User.withDefaultPasswordEncoder();
//        auth.inMemoryAuthentication()
//                .withUser(userBuilder
//                        .username("egor")
//                        .password("egor")
//                        .roles("EMPLOYEE"))
//                .withUser(userBuilder
//                        .username("vlad")
//                        .password("vlad")
//                        .roles("HR"))
//                .withUser(userBuilder
//                        .username("ivan")
//                        .password("ivan")
//                        .roles("MANAGER", "HR"));

    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers(HttpMethod.GET, "/employees/").permitAll()
                .antMatchers(HttpMethod.POST, "/employees/").permitAll()
                .antMatchers(HttpMethod.DELETE, "/employees/").permitAll()
//
//                .antMatchers(HttpMethod.GET, "/departments/**").permitAll()
//                .antMatchers(HttpMethod.POST, "/departments/**").permitAll()
//
//                .antMatchers(HttpMethod.GET, "/users/**").permitAll()
//                .antMatchers(HttpMethod.POST, "/users/**").hasAnyRole("HR")
//                .antMatchers(HttpMethod.DELETE, "/users/**").hasRole("HR")
                .anyRequest().authenticated()
                .and().httpBasic()
                .and().sessionManagement().disable();

    }
}
