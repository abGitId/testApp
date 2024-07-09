package com.test.testApp.enums;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public enum UserAuthorizationType {

    NO_RESOURCE, NONE, GUEST, REGISTERED_MEMBER, ADMIN;

    public boolean canView() {
        return this.ordinal() > 1;
    }

    public boolean canAddOrEdit() {
        return this.ordinal() > 2;
    }

    public boolean canDelete() {
        return this.ordinal() > 3;
    }


    public ResponseEntity getResponseIfNotPermitted() {
        return switch (this) {
            case NO_RESOURCE -> new ResponseEntity<>("not found", HttpStatus.NOT_FOUND);
            case NONE -> new ResponseEntity<>("not a view permission", HttpStatus.FORBIDDEN);
            case GUEST -> new ResponseEntity<>("no edit permission", HttpStatus.FORBIDDEN);
            case REGISTERED_MEMBER -> new ResponseEntity<>("no delete permission", HttpStatus.FORBIDDEN);
            default -> new ResponseEntity<>("access all pages", HttpStatus.OK);
        };
    }
}
