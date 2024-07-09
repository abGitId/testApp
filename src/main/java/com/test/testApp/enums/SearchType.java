package com.test.testApp.enums;

public enum SearchType {
    EMPLOYEE_SEARCH_BY_NAME("name"),
    EMPLOYEE_SEARCH_BY_LASTNAME("lastName"),
    EMPLOYEE_SEARCH_BY_CITY("city"),
    EMPLOYEE_SEARCH_BY_DEPT("department"),
    EMPLOYEE_SEARCH_BY_AGE("age");

    private String searchTypeId;

    SearchType(String searchTypeId) {
        this.searchTypeId = searchTypeId;
    }

    public String getSearchTypeId() {
        return searchTypeId;
    }
}
