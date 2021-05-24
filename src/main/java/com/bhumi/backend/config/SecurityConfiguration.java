package com.bhumi.backend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.csrf().disable()
            .authorizeRequests()
                .antMatchers("/api/posts", "/api/forums").permitAll()
                .antMatchers("/api/auth/admin/**").hasRole("ADMIN")
                .antMatchers("/api/auth/**").hasAnyRole("ADMIN", "USER")
                .and()
            .formLogin()
                .loginPage("/api/auth/login")
                .permitAll()
                .and()
//            .logout()
//                .permitAll()
//                .and()
            .httpBasic();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        String bcryptPass = this.passwordEncoder().encode("adminpass");
        auth.inMemoryAuthentication().withUser("admin").password(bcryptPass).roles("ADMIN");
    }

    @Bean
    protected PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
