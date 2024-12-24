package com.example.ex1.Config;

import com.example.ex1.Authentication.JWTAuthenticationFilter;
import com.example.ex1.Service.UserDetailServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig {

    private final UserDetailServiceImpl myUserDetailsService;
//    private AuthenticationManager authenticationManager;
    private final JWTAuthenticationFilter jwtAuthenticationFilter;

    public SecurityConfig(UserDetailServiceImpl myUserDetailsService, JWTAuthenticationFilter jwtAuthenticationFilter) {
        this.myUserDetailsService = myUserDetailsService;
        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
//        this.authenticationManager = authenticationManager;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
       return http
                .csrf(customizer ->customizer.disable())
                .authorizeHttpRequests(c-> c
                        .requestMatchers("/login.html", "/auth/**", "/oauth2/**").permitAll()
                        .requestMatchers("/students").hasRole("USER")
                        .anyRequest().authenticated())
                .formLogin(form -> form
                .loginPage("/login.html") // Serve your custom login pagedpoint for processing form login
                .defaultSuccessUrl("/home", true) // Redirect on successful login
                .permitAll())
                .oauth2Login(oauth -> oauth
                        .loginPage("/login.html") // Serve the same login page for OAuth
                        .defaultSuccessUrl("/home", true))
               .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
               .build();
    }

    @Bean
    public AuthenticationConfiguration authenticationConfiguration() {
        return new AuthenticationConfiguration();
    }
    @Bean
    public AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(new BCryptPasswordEncoder(12));
        provider.setUserDetailsService(myUserDetailsService);
        return provider;
    }
}
