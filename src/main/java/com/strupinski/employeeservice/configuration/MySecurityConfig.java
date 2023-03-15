package com.strupinski.employeeservice.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;

import javax.sql.DataSource;

@EnableWebSecurity
public class MySecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    DataSource dataSource;
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication().dataSource(dataSource);
//        User.UserBuilder userBuilder = User.withDefaultPasswordEncoder();
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
        http.authorizeRequests()
                .antMatchers(HttpMethod.GET, "/employees").hasAnyRole( )
                .antMatchers(HttpMethod.DELETE, "/employees/{id}").hasAnyAuthority("ROLE_HR", "MANAGER")
                .antMatchers("/employees/{id}").hasRole("HR")
                .and()
                .httpBasic()
                .and()
                .formLogin()
                .permitAll();
    }
}
