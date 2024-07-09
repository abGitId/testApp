package com.test.testApp.client.config;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
//import org.springframework.security.oauth2.client.OAuth2AuthorizeRequest;
//import org.springframework.security.oauth2.client.OAuth2AuthorizedClientManager;
import org.springframework.stereotype.Component;

//@Component
public class OAuth2AuthorizationTokenProvider {
    private static final Authentication ANONYMOUS_AUTH_TOKEN = new AnonymousAuthenticationToken("key", "anonymous"
            , AuthorityUtils.createAuthorityList(new String[]{"ROLE_ANONYMOUS"}));

   /* @Autowired
    private OAuth2AuthorizedClientManager authorizedClientManager;

    public String getAuthToken(final String oauth2ClientRegistrationId) {
        OAuth2AuthorizeRequest request = OAuth2AuthorizeRequest.withClientRegistrationId(oauth2ClientRegistrationId)
                .principal(ANONYMOUS_AUTH_TOKEN).build();

        return String.format("Bearer %s", authorizedClientManager.authorize(request).getAccessToken().getTokenValue());

    }*/

}
