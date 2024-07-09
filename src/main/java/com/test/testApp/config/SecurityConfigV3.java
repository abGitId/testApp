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
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.header.HeaderWriterFilter;
import org.springframework.security.web.header.writers.ReferrerPolicyHeaderWriter;

//@Configuration
//@EnableMethodSecurity(securedEnabled = true)
//@Profile("!local && !dev")
public class SecurityConfigV3 {

    public static final String[] WHITELIST_URLS = {
            "/swagger-ui*/**"
    };

    @Autowired
    private UserAuthenticationProvider userAuthenticationProvider;

    @Autowired
    private UserAuthenticationProcessingFilter userAuthenticationProcessingFilter;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.csrf(httpSecurityCsrfConfigurer -> httpSecurityCsrfConfigurer.disable())
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and().authorizeRequests(request -> request.requestMatchers(WHITELIST_URLS).permitAll())
                .authorizeRequests(request -> request.anyRequest().authenticated())
                .addFilterAfter(userAuthenticationProcessingFilter, HeaderWriterFilter.class)
                .authenticationProvider(userAuthenticationProvider)
                .headers().contentSecurityPolicy("default-src 'self'")
                .and().referrerPolicy(ReferrerPolicyHeaderWriter.ReferrerPolicy.SAME_ORIGIN);
        return httpSecurity.build();
    }

    //@Bean
    public InMemoryUserDetailsManager userDetailsManagerService() {

        // approach 1 where we use withDefaultPasswordEncoder method while creating user details
//        UserDetails admin = User.withDefaultPasswordEncoder().username("admin").password("12345")
//                .authorities("admin").build();
//        UserDetails user = User.withDefaultPasswordEncoder().username("user").password("12345")
//                .authorities("read").build();
//        return new InMemoryUserDetailsManager(admin, user);
        UserDetails admin = User.withUsername("admin").password("12345")
                .authorities("admin").build();
        UserDetails user = User.withUsername("user").password("12345")
                .authorities("read").build();
        return new InMemoryUserDetailsManager(admin, user);
    }

    //@Bean
    public PasswordEncoder passwordEncoder(){
        return NoOpPasswordEncoder.getInstance();
    }
}
