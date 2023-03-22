package com.strupinski.employeeservice.security.config;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.www.BasicAuthenticationEntryPoint;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserDetailsService userDetailsService;


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        System.out.println("SecurityConfig.configure with builder");
        auth.userDetailsService(userDetailsService);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        System.out.println("SecurityConfig.configure");
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers(HttpMethod.GET, "/employees/**").hasAnyAuthority("HR", "EMPLOYEE")
                .antMatchers(HttpMethod.POST, "/employees/**").hasAuthority("HR")
                .antMatchers(HttpMethod.DELETE, "/employees/**").hasAuthority("HR")
                //
                .antMatchers(HttpMethod.GET, "/departments/**").hasAnyAuthority("HR", "EMPLOYEE")
                .antMatchers(HttpMethod.POST, "/departments/**").hasAuthority("HR")
                .antMatchers(HttpMethod.DELETE, "/departments/**").hasAuthority("HR")
                //
                .antMatchers(HttpMethod.GET, "/users/**").hasAnyAuthority("HR", "EMPLOYEE")
                .antMatchers(HttpMethod.POST, "/users/**").hasAuthority("HR")
                .antMatchers(HttpMethod.DELETE, "/users/**").hasAuthority("HR")
                .anyRequest().authenticated()
                .and()
                .httpBasic()
                .and().sessionManagement().disable();

    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
