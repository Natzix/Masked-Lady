package org.natzi.maskedlady.config.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.logging.Logger;
import java.util.stream.Stream;

@Component
public class CustomFilterJWT extends OncePerRequestFilter {

    private static final String BEARER_PREFIX = "Bearer ";
    private final AntPathMatcher antMatcher = new AntPathMatcher();
    private final Logger lg = Logger.getLogger(CustomFilterJWT.class.getName());



    // Evita que se ingrese al filtro de autenticación y autorizacion
    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        String path = request.getRequestURI();
        return Stream.of(EndPointSecurity.PERMIT_SN_JWT)
                .anyMatch(pattern -> antMatcher.match(pattern, path));
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String header = request.getHeader("Authorization");

        if (header == null || !header.startsWith(BEARER_PREFIX)) {
            filterChain.doFilter(request, response);
            return;
        }
        String jwt = header.substring(BEARER_PREFIX.length());

        filterChain.doFilter(request, response);
    }
}
