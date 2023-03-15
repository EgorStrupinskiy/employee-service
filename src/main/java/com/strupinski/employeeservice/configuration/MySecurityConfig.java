package com.strupinski.employeeservice.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class MySecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    DataSource dataSource;

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .jdbcAuthentication()
                .dataSource(dataSource)
                .usersByUsernameQuery("select username, password, enabled from users where username=?")
                .authoritiesByUsernameQuery("select username, authority from authorities where username=?");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/employees").hasRole("HR")
                .antMatchers(HttpMethod.POST, "/employees").hasRole("MANAGER")
                .antMatchers(HttpMethod.DELETE, "/employees/{id}").hasRole("HR")
                .anyRequest().authenticated()
                .and()
            .formLogin()
                .permitAll()
                .and()
                .logout()
                .permitAll();

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
