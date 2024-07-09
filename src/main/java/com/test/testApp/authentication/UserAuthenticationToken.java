package com.test.testApp.authentication;

import lombok.Value;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.List;

@Value
public class UserAuthenticationToken extends AbstractAuthenticationToken {
    private final UserInfo principal;
    private final UserAuthenticationDetail credentials;

    public UserAuthenticationToken(UserInfo principal, UserAuthenticationDetail credentials, List<SimpleGrantedAuthority> authorities) {
        super(authorities);
        this.principal = principal;
        this.credentials = credentials;
    }
}
