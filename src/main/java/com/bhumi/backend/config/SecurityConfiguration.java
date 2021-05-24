package com.bhumi.backend.config;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
	private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

	@Autowired
	private UserDetailsService userDetailsService;

	@Autowired
	private JwtRequestFilter jwtRequestFilter;

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.csrf().disable()
            .authorizeRequests()
                .antMatchers("/api/posts", "/api/forums").permitAll()
                .antMatchers("/api/auth/admin/**").hasRole("ADMIN")
                .antMatchers("/api/auth/**").hasAnyRole("ADMIN", "USER")
                .and()
				.exceptionHandling().authenticationEntryPoint(jwtAuthenticationEntryPoint)
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
            .formLogin()
                .loginPage("/api/auth/login")
                .failureUrl("/auth/login?error=true")
                .failureHandler(new AuthenticationFailureHandler(){
                    @Override
                    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
                            AuthenticationException exception) throws IOException, ServletException {
                        String email = request.getParameter("email");
                        String error = exception.getMessage();
                        System.out.println("A failed login attempt with email: " + email + " and password: " + request.getParameter("password") + ". Reason: " + error);
                        // String redirectUrl = request.getContextPath().replace("/api", "") + "/login";
                        // response.sendRedirect(redirectUrl);
                        response.setStatus(HttpStatus.UNAUTHORIZED.value());
                    }
                })
                .permitAll()
                .and()
//            .logout()
//                .permitAll()
//                .and()
            .httpBasic();
        httpSecurity.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
    }

    @Autowired
    protected void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    @Bean
    protected PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean(BeanIds.AUTHENTICATION_MANAGER)
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}
}
