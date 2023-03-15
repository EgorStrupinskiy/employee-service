package com.strupinski.employeeservice.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class MySecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    DataSource dataSource;

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
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/employees").hasRole("HR")
                .antMatchers(HttpMethod.POST, "/employees").hasRole("MANAGER")
                .antMatchers(HttpMethod.DELETE, "/employees/{id}").hasRole("HR")
                .anyRequest().authenticated()
                .and()
                .httpBasic();



//        .authorizeRequests()
//                .antMatchers("/employees").hasRole("HR")
//                .antMatchers(HttpMethod.POST, "/employees").hasRole("MANAGER")
//                .antMatchers(HttpMethod.DELETE, "/employees/{id}").hasRole("HR")
//                .and()
//                .httpBasic()
//                .and()
//                .formLogin()
//                .permitAll();
    }
}
