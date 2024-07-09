package com.test.testApp.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "testclient", url = "http://localhost:8081/test")
public interface TestFeignClient {

    @GetMapping(path = "/client")
    String getClients();
}
