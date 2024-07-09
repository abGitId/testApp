package com.test.testApp.authentication;

import lombok.Value;

import java.io.Serializable;

@Value
public class UserInfo implements Serializable {

    String id;
    String name;
    String email;
    String userType;
}
