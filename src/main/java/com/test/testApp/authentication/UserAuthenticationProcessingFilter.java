package com.test.testApp.authentication;

import com.test.testApp.enums.UserAuthorizationType;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
@Slf4j
public class UserAuthenticationProcessingFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        UserAuthenticationDetail userAuthenticationDetail = getUserAuthenticationDetails(request);
        UserInfo userInfo = new UserInfo(request.getHeader("loginId"), "", "", "");
        List<SimpleGrantedAuthority> authorities = getUserAuthorities(request);
        if (userAuthenticationDetail.isValid()) {
            UserAuthenticationToken userAuthenticationToken = new UserAuthenticationToken(userInfo, userAuthenticationDetail, authorities);
            SecurityContextHolder.getContext().setAuthentication(userAuthenticationToken);
        }

        filterChain.doFilter(request, response);
    }

    public List<SimpleGrantedAuthority> getUserAuthorities(HttpServletRequest request) {
        final List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        String authType = request.getHeader("authType");
        authorities.add(new SimpleGrantedAuthority(StringUtils.hasText(authType) ? authType : "GUEST"));
        //-- authorities.add(new SimpleGrantedAuthority(UserAuthorizationType.GUEST.name()));
        return authorities;
    }


    private UserAuthenticationDetail getUserAuthenticationDetails(HttpServletRequest request) {
        String clientId = request.getHeader("clientId");
        String secret = request.getHeader("clientSecret");
        return new UserAuthenticationDetail(clientId, secret);
    }
}
