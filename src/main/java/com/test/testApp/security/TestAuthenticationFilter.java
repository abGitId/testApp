package com.test.testApp.security;

import com.test.testApp.enums.UserAuthorizationType;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

//@Component
//@Order(1)
@Slf4j
public class TestAuthenticationFilter extends OncePerRequestFilter {

    public static final String[] WHITELIST_URLS = {
            "/swagger-ui*/**"
    };

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        if (authenticateRequest(request)) {
            log.info("Authentication successful!!!");
            SecurityContext context = SecurityContextHolder.getContext();
            context.setAuthentication(getAuthentication(request));
            doFilter(request, response, filterChain);
        } else {
            log.info("request authentication failed!!!");
            response.sendError(HttpServletResponse.SC_FORBIDDEN);
        }
    }

    private Authentication getAuthentication(HttpServletRequest request) {
        log.info("Setting up authentication");
        final List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(UserAuthorizationType.GUEST.name()));
        return new UsernamePasswordAuthenticationToken("login-header", "token", authorities);
    }

    private boolean authenticateRequest(HttpServletRequest request) {
        return true;
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        for (String url : WHITELIST_URLS) {
            var antPathRequestMatcher = new AntPathRequestMatcher(url);
            if (antPathRequestMatcher.matches(request)) {
                return true;
            }
        }
        return false;
    }
}
