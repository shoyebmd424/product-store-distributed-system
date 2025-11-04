package com.userService.config;

import com.common.security.JwtUtil;
import com.common.security.MyFilterChain;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableMethodSecurity
public class SecurityConfig {

    @Autowired
    private MyFilterChain filterChain;
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
     return http.csrf(AbstractHttpConfigurer::disable)
             .authorizeHttpRequests(auth->
                     auth.requestMatchers("/user/auth/**","/actuator/**").permitAll()
                     .anyRequest().authenticated()).exceptionHandling(exception -> exception
                     .authenticationEntryPoint(new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED)) // 401
                     .accessDeniedHandler((req, res, ex) -> {
                         res.sendError(HttpStatus.FORBIDDEN.value(), "Access Denied");
                     })
             )
             .sessionManagement(session->
                     session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
             .addFilterBefore(filterChain, UsernamePasswordAuthenticationFilter.class).build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
