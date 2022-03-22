package org.boygear;

import org.boygear.services.userServices.UserDetailsServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public UserDetailsService userDetailsService() {
        return new UserDetailsServiceImpl();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService());
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()

                .antMatchers(HttpMethod.GET, "/measurement/**").permitAll()
                .antMatchers(HttpMethod.GET, "/stations/**").permitAll()
                .antMatchers(HttpMethod.POST, "/users").permitAll()
                .antMatchers(HttpMethod.PUT, "/users/*").hasAnyRole("USER","ADMIN")
                .antMatchers(HttpMethod.GET, "/users/*").hasAnyRole("USER","ADMIN")
                .antMatchers(HttpMethod.GET, "/users/*/favoritestations").hasAnyRole("USER","ADMIN")
                .antMatchers(HttpMethod.POST, "/users/*/favoritestations").hasAnyRole("USER","ADMIN")
                .antMatchers(HttpMethod.DELETE, "/users/*").hasAnyRole("USER","ADMIN")
                .antMatchers("/**").hasRole("ADMIN")
                .and()
                .formLogin().loginProcessingUrl("/login").defaultSuccessUrl("/stations", true).permitAll()
                .and()
                .csrf().disable()
                .logout().permitAll();
    }
}