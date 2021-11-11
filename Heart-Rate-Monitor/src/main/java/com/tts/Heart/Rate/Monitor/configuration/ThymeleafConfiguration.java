package com.tts.Heart.Rate.Monitor.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.thymeleaf.extras.springsecurity5.dialect.SpringSecurityDialect;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class ThymeleafConfiguration extends WebSecurityConfigurerAdapter {


    @Autowired
    private DataSource dataSource;

    @Value("${spring.queries.users-query}")
    private String usersQuery;

    @Value("${spring.queries.roles-query}")
    private String rolesQuery;


    //Add username and password authentication
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        String password = encoder().encode("password");

//        auth
//                .inMemoryAuthentication()
//                .withUser("user")
//                .password(password)
//                .roles("USER")
//                .and()
//                .passwordEncoder(encoder());

        auth
                .jdbcAuthentication()
                .usersByUsernameQuery(usersQuery)
                .authoritiesByUsernameQuery(rolesQuery)
                .dataSource(dataSource)
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
                .antMatchers("/endusers").permitAll()
                .antMatchers("/insert").permitAll()
                .antMatchers("readings").permitAll()
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
