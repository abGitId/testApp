package com.test.testApp.strategy.employee;

import com.test.testApp.enums.SearchType;
import com.test.testApp.model.Employee;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class SearchEmployeeByDept implements IEmployeeSearchStrategy {
    @Override
    public List<Employee> searchData(String searchStr) {
        return Employee.getEmployees().stream().filter(employee ->
                employee.getDepartment().equalsIgnoreCase(searchStr)
        ).collect(Collectors.toList());
    }

    @Override
    public SearchType getSearchType() {
        return SearchType.EMPLOYEE_SEARCH_BY_DEPT;
    }
}
