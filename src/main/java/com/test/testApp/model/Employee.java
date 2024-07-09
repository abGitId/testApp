package com.test.testApp.model;

import com.test.testApp.enums.EventType;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Employee {
    long id;
    String firstName;
    String lastName;
    String city;
    int age;
    String department;
    EventType eventType;


    public static List<Employee> getEmployees() {
        Employee emp1 = new Employee(1, "user1", "T12", "pune", 30, "ADMIN", EventType.ADD_USER);
        Employee emp2 = new Employee(2, "user2", "lastName", "mumbai", 30, "ADMIN", EventType.ADD_USER);
        Employee emp3 = new Employee(3, "user3", "lastName", "pune", 30, "IT", EventType.DELETE_USER);
        Employee emp4 = new Employee(4, "user4", "T123", "mumbai", 30, "IT", EventType.PROP_CHANGE);
        List<Employee> employees = new ArrayList<>();
        employees.add(emp1);
        employees.add(emp2);
        employees.add(emp3);
        employees.add(emp4);
        return employees;
    }
}
