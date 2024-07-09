package com.test.testApp.client.config;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
//import org.springframework.security.oauth2.client.OAuth2AuthorizeRequest;
//import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
//import org.springframework.security.oauth2.client.OAuth2AuthorizedClientManager;
//import org.springframework.security.oauth2.client.registration.ClientRegistration;

import java.util.Objects;

@Slf4j
@AllArgsConstructor
public class OAuthClientCredentialsFeignManager {
    private static final Authentication ANONYMOUS_AUTH_TOKEN = new AnonymousAuthenticationToken("key", "anonymous"
            , AuthorityUtils.createAuthorityList(new String[]{"ROLE_ANONYMOUS"}));
   /* private OAuth2AuthorizedClientManager manager;
    private ClientRegistration clientRegistration;
    public String getAccessToken() {
        try {
            OAuth2AuthorizeRequest oAuth2AuthorizeRequest = OAuth2AuthorizeRequest
                    .withClientRegistrationId(clientRegistration.getRegistrationId())
                    .principal(ANONYMOUS_AUTH_TOKEN)
                    .build();
            OAuth2AuthorizedClient client = manager.authorize(oAuth2AuthorizeRequest);
            if (Objects.isNull(client)) {
                throw new IllegalStateException("client credentials flow on " + clientRegistration.getRegistrationId() + " failed, client is null");
            }
            return client.getAccessToken().getTokenValue();
        } catch (Exception exp) {
            log.error("client credentials error " + exp.getMessage());
        }
        return null;
    }*/
}
