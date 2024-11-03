package com.example.springbootblogapplication.config;

import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.springframework.security.web.util.matcher.AntPathRequestMatcher.antMatcher;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(securedEnabled = true)
public class WebSecurityConfig {

    private static final Logger logger = LoggerFactory.getLogger(WebSecurityConfig.class);

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        // Note: spring security requestMatchers updated again
        // https://stackoverflow.com/questions/76809698/spring-security-method-cannot-decide-pattern-is-mvc-or-not-spring-boot-applicati
        http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth -> {
                    auth.requestMatchers(antMatcher("/css/**")).permitAll();
                    auth.requestMatchers(antMatcher("/js/**")).permitAll();
                    auth.requestMatchers(antMatcher("/images/**")).permitAll();
                    auth.requestMatchers(antMatcher("/fonts/**")).permitAll();
                    auth.requestMatchers(antMatcher("/webjars/**")).permitAll();
                    auth.requestMatchers(antMatcher("/")).permitAll();
                    auth.requestMatchers(antMatcher("/rss/**")).permitAll();
                    auth.requestMatchers(antMatcher("/register/**")).permitAll();
                    auth.requestMatchers(antMatcher("/posts/**")).permitAll();
                    auth.requestMatchers(antMatcher("/language/**")).permitAll(); // Add this line
                    auth.requestMatchers(PathRequest.toH2Console()).permitAll();
                    auth.anyRequest().authenticated();
                })

                .formLogin(form -> form
                        .loginPage("/login")
                        .loginProcessingUrl("/login")
                        .usernameParameter("email")
                        .passwordParameter("password")
                        .defaultSuccessUrl("/")
                        .failureUrl("/login?error")
                        .successHandler(authenticationSuccessHandler())
                        .failureHandler((request, response, exception) -> {
                            logger.error("Authentication failed: " + exception.getMessage());
                            response.sendRedirect("/login?error");
                        })
                        .permitAll());

        return http.build();
    }

    @Bean
    public AuthenticationSuccessHandler authenticationSuccessHandler() {
        return new SimpleUrlAuthenticationSuccessHandler() {
            @Override
            public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                    Authentication authentication) throws IOException, ServletException {
                System.out.println("User '" + authentication.getName() + "' has successfully authenticated");
                super.onAuthenticationSuccess(request, response, authentication);
            }
        };
    }
}
