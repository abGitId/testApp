package com.test.testApp.controller;

import com.test.testApp.async.AsyncService;
import com.test.testApp.client.TestFeignClient;
import com.test.testApp.customannotations.DenyGuestAccess;
import com.test.testApp.enums.EventType;
import com.test.testApp.enums.SearchType;
import com.test.testApp.enums.UserAuthorizationType;
import com.test.testApp.model.Employee;
import com.test.testApp.service.EmployeeService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RequestMapping("/util")
@RestController
@Slf4j
@AllArgsConstructor
public class UtilController {

    private final TestFeignClient testFeignClient;

    private final AsyncService asyncService;

    private final EmployeeService employeeService;

    @GetMapping("/test")
    public ResponseEntity<String> greetings() {
        return ResponseEntity.ok("Test App is running...");
    }

    @GetMapping("/test-async")
    public ResponseEntity<String> testAsync() {
        asyncService.testAsync();
        return ResponseEntity.ok("Async call started...");
    }

    @GetMapping("/test-without-supply-async")
    public ResponseEntity<String> testWithoutSupplyAsync() {
        return ResponseEntity.ok("Test without supply Async call completed..." + asyncService.testWithoutSupplyAsyncCall());
    }

    @GetMapping("/test-supply-async")
    public ResponseEntity<String> testSupplyAsync() {
        return ResponseEntity.ok("Test with supply Async call completed..." + asyncService.testWithSupplyAsyncCall());
    }

    @GetMapping("/test-run-async")
    public ResponseEntity<String> testRunAsync() {
        return ResponseEntity.ok("Test with run Async call completed..." + asyncService.testWithRunAsyncCall());
    }

    @GetMapping("/test-client")
    public ResponseEntity<String> testClient() {
        return ResponseEntity.ok(testFeignClient.getClients());
    }

    @GetMapping("/test-strategy-search")
    public ResponseEntity<List<Employee>> searchStrategy(@RequestParam SearchType searchType, @RequestParam String searchStr) {
        return ResponseEntity.ok(employeeService.searchEmployee(searchType, searchStr));
    }

    @GetMapping("/test-enum-auth")
    public ResponseEntity testEnumAuth(@RequestParam UserAuthorizationType authType) {
        return authType.getResponseIfNotPermitted();
    }

    @GetMapping("/test-enum-function")
    public ResponseEntity<String> testEnumFUnction(@RequestParam EventType eventType) {
        String logStr = "No event found";
        Optional<Employee> employeeOptional = Employee.getEmployees().stream().filter(employee -> employee.getEventType().equals(eventType)).findFirst();
        if (employeeOptional.isPresent()) {
            Employee emp = employeeOptional.get();
            logStr = emp.getEventType().generate(emp);

        }
        return ResponseEntity.ok(logStr);
    }

    @DenyGuestAccess
    @GetMapping("/test-deny-guest")
    public ResponseEntity testDenyGuest() {

        SecurityContextHolder.getContext().getAuthentication().getAuthorities().stream().forEach(grantedAuthority -> System.out.println("grantedAuthority.getAuthority() = " + grantedAuthority.getAuthority()));
        return ResponseEntity.ok("Test App is running...");
    }
}
