package com.test.testApp.config;

import com.test.testApp.security.TestAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.web.header.HeaderWriterFilter;
import org.springframework.security.web.header.writers.ReferrerPolicyHeaderWriter;

@Configuration
@EnableWebSecurity
@Profile({"local", "dev"})
public class SecurityConfig {

    public static final String[] WHITELIST_URLS = {
            "/swagger-ui*/**"
    };

    @Autowired
    private TestAuthenticationFilter  testAuthenticationFilter;
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception{
        httpSecurity.csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and().authorizeRequests().requestMatchers(WHITELIST_URLS).permitAll()
                .anyRequest().authenticated()
                .and().addFilterAfter(testAuthenticationFilter, HeaderWriterFilter.class)
                .headers().contentSecurityPolicy("default-src 'self'")
                .and().referrerPolicy(ReferrerPolicyHeaderWriter.ReferrerPolicy.SAME_ORIGIN);
        return httpSecurity.build();
    }
}