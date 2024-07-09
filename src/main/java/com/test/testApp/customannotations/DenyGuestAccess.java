package com.test.testApp.customannotations;

import org.springframework.security.access.prepost.PreAuthorize;

import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Inherited
@Retention(RetentionPolicy.RUNTIME)
@PreAuthorize("!hasAuthority(T(com.test.testApp.enums.UserAuthorizationType).GUEST.name())")
public @interface DenyGuestAccess {
}
