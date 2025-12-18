package org.natzi.maskedlady.config.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.logging.Logger;
import java.util.stream.Stream;

@Component
public class MaskedFilterJwt extends OncePerRequestFilter {
    private static final String BEARER_PREFIX = "Bearer ";
    private AntPathMatcher antMatcher = new AntPathMatcher();

    private final Logger lg = Logger.getLogger(MaskedFilterJwt.class.getName());

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
            response.sendError(HttpStatus.BAD_REQUEST.value(), "The request cannot be processed");
            return;
        }
        lg.info("Pasa por este filrtro");
        filterChain.doFilter(request, response);
    }
}
