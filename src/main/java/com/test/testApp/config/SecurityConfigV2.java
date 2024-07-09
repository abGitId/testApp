package com.test.testApp.config;

import com.test.testApp.authentication.UserAuthenticationProcessingFilter;
import com.test.testApp.authentication.UserAuthenticationProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.header.HeaderWriterFilter;
import org.springframework.security.web.header.writers.ReferrerPolicyHeaderWriter;

@Configuration
@EnableMethodSecurity(securedEnabled = true)
@Profile("!local && !dev")
public class SecurityConfigV2 {

    public static final String[] WHITELIST_URLS = {
            "/swagger-ui*/**"
    };

    @Autowired
    private UserAuthenticationProvider userAuthenticationProvider;

    @Autowired
    private UserAuthenticationProcessingFilter userAuthenticationProcessingFilter;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and().authorizeRequests().requestMatchers(WHITELIST_URLS).permitAll()
                .anyRequest().authenticated()
                .and().addFilterAfter(userAuthenticationProcessingFilter, HeaderWriterFilter.class)
                .authenticationProvider(userAuthenticationProvider)
                .headers().contentSecurityPolicy("default-src 'self'")
                .and().referrerPolicy(ReferrerPolicyHeaderWriter.ReferrerPolicy.SAME_ORIGIN);
        return httpSecurity.build();
    }
}
