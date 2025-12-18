package org.natzi.maskedlady.config;

import org.natzi.maskedlady.config.filter.MaskedFilterJwt;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import java.util.logging.Logger;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final Logger lg = Logger.getLogger(SecurityConfig.class.getName());

    @Bean
    public SecurityFilterChain  filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests((authorize) -> { authorize
                        .anyRequest().permitAll();
                                /*.requestMatchers(HttpMethod.POST, EndPointSecurity.ENDPOINT).authenticated()
                        .anyRequest().permitAll();*/
                })
                .addFilterAt(new MaskedFilterJwt(), BasicAuthenticationFilter.class);
        return http.build();
    }
}