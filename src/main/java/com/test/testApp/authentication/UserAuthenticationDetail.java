package com.test.testApp.authentication;

import lombok.Value;
import org.springframework.util.StringUtils;

@Value
public class UserAuthenticationDetail {

    String clientId;
    String secret;

    public boolean isValid() {
        return StringUtils.hasLength(clientId) && StringUtils.hasLength(secret);
    }
}
