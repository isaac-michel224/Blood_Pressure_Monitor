package com.tts.Heart.Rate.Monitor.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.thymeleaf.extras.springsecurity5.dialect.SpringSecurityDialect;

@Configuration
@EnableWebSecurity
public class ThymeleafConfiguration extends WebSecurityConfigurerAdapter {

    //Add username and password authentication
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        String password = encoder().encode("password");

        auth
                .inMemoryAuthentication()
                .withUser("user")
                .password(password)
                .roles("USER")
                .and()
                .passwordEncoder(encoder());

    }

//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http.authorizeRequests()
////                .antMatchers("/",
////                        "UserProfile").permitAll()
////                .antMatchers("/admin/**").hasRole('ADMIN')
//                .antMatchers("/console/**").permitAll()
//                .and()
//                .formLogin();
//        http.headers().frameOptions().disable();

//    }
//Enables web pages and H2 DATABASE
    @Override
    protected void configure (HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/console/**").permitAll()
                .antMatchers("/login/**").permitAll()
                .antMatchers("/endusers/{username}").permitAll()
                .antMatchers("/blood-pressure-monitor/insert").permitAll()
                .antMatchers("/blood-pressure-monitor/readings").permitAll()
                .antMatchers("/users/**").permitAll()
                .antMatchers("/save/**").permitAll()
                .antMatchers("/info/**").permitAll()
                .antMatchers().hasAnyAuthority("USER").anyRequest()
                .authenticated().and().csrf().disable().formLogin()
                        .loginPage("/login").failureUrl("/login?error=true")
                .defaultSuccessUrl("/info")
                        .and().logout();
        http.headers().frameOptions().disable();
    }


    @Bean
    public SpringSecurityDialect springSecurityDialect() {
        return new SpringSecurityDialect();
    }

    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }
}
