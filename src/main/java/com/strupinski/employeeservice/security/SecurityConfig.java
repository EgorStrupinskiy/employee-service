package com.strupinski.employeeservice.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    DataSource dataSource;

    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication().dataSource(dataSource);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers(HttpMethod.GET, "/employees/").permitAll()
                .antMatchers(HttpMethod.POST, "/employees/").hasAnyRole("HR")
                .antMatchers(HttpMethod.DELETE, "/employees/**").hasRole("HR")
                .antMatchers(HttpMethod.POST, "/users/").hasAnyRole("HR")
                .antMatchers(HttpMethod.DELETE, "/users/**").hasRole("HR")
                .anyRequest().authenticated()
                .and()
                .httpBasic();
    }
}
