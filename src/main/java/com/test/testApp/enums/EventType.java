package com.test.testApp.enums;

import com.test.testApp.model.Employee;

import java.util.function.BiFunction;

public enum EventType {

    ADD_USER("has been added", EventType::generateLogStr, "Added"),
    DELETE_USER("has been deleted ", EventType::generateLogStr, "Deleted"),
    PROP_CHANGE("property has been changed from {X} to {Y}", EventType::generatePropertyLogStr, "");

    private String logStr;

    private BiFunction<EventType, Employee, StringBuilder> biFunction;
    private String action;

    EventType(String logStr, BiFunction<EventType, Employee, StringBuilder> biFunction, String action) {
        this.logStr = logStr;
        this.biFunction = biFunction;
        this.action = action;
    }

    public String generate(Employee employee) {
        return biFunction.apply(this, employee).toString();
    }

    private static StringBuilder generateLogStr(EventType type, Employee employee) {
        StringBuilder builder = new StringBuilder("");
        return builder.append(employee.getFirstName()).append(" ").append(type.logStr);
    }

    private static StringBuilder generatePropertyLogStr(EventType type, Employee employee) {
        StringBuilder builder = new StringBuilder("");

        return builder.append(employee.getDepartment()).append(" ").append(type.logStr.replace("{X}", employee.getFirstName()).replace("{Y}", employee.getLastName()));
    }

}
