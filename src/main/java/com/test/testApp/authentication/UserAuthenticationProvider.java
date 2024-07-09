package com.test.testApp.authentication;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;


@Component
@Slf4j
public class UserAuthenticationProvider implements AuthenticationProvider {

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        UserAuthenticationDetail authenticationDetail = (UserAuthenticationDetail) authentication.getCredentials();
        if (isValidUser(authenticationDetail)) {
            log.info("Authentication Successful!! {}", authenticationDetail);
            authentication.setAuthenticated(true);
            return authentication;
        } else {
            throw new AccessDeniedException("Authentication failed!!!");
        }

    }

    private boolean isValidUser(UserAuthenticationDetail authenticationDetail) {
        return true;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return UserAuthenticationToken.class.isAssignableFrom(authentication);
    }
}
