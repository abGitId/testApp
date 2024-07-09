package com.test.testApp.strategy.employee;

import com.test.testApp.enums.SearchType;
import com.test.testApp.model.Employee;

import java.util.List;

public interface IEmployeeSearchStrategy {

    List<Employee> searchData(String searchStr);

    SearchType getSearchType();
}
