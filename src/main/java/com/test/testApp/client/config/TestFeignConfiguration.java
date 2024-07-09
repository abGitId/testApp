package com.test.testApp.client.config;

import feign.RequestInterceptor;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.openfeign.security.OAuth2AccessTokenInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//@Configuration
@RequiredArgsConstructor
public class TestFeignConfiguration {
  /*  private static final String CLIENT_OAUTH2_REGISTRATION_ID = "testclient";
    private final OAuth2AuthorizationTokenProvider oAuth2AuthorizationTokenProvider;

   // @Bean
    public RequestInterceptor authInterceptor() {
        return requestTemplate -> requestTemplate.header(OAuth2AccessTokenInterceptor.AUTHORIZATION
                , oAuth2AuthorizationTokenProvider.getAuthToken(CLIENT_OAUTH2_REGISTRATION_ID));
    }*/
}
