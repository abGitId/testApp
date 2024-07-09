package com.test.testApp.config;

import feign.Logger;
import feign.Retryer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;


@Configuration
@Slf4j
public class FeignClientConfig {

    @Bean
    Logger.Level feignLoggerLevel(){
        return Logger.Level.FULL;
    }

    @Bean
    public Retryer retryer(){
        return new Retryer.Default(100, TimeUnit.SECONDS.toMillis(2L), 3);
    }
}
