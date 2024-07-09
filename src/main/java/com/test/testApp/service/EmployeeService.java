package com.test.testApp.service;

import com.test.testApp.enums.SearchType;
import com.test.testApp.model.Employee;
import com.test.testApp.strategy.employee.EmployeeSearchFactory;
import com.test.testApp.strategy.employee.IEmployeeSearchStrategy;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class EmployeeService {

    @Autowired
    private EmployeeSearchFactory employeeSearchFactory;

    public List<Employee> searchEmployee(SearchType searchType, String searchStr) {

        IEmployeeSearchStrategy employeeSearchStrategy = employeeSearchFactory.getEmployeeSearchStrategy(searchType);
        return employeeSearchStrategy.searchData(searchStr);
    }
}
