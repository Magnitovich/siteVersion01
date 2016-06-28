package com.example.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.Set;

@ResponseStatus(value= HttpStatus.EXPECTATION_FAILED, reason="User should not have more that one role")
public class UserHasMoreThatOneRoleException extends RuntimeException {
    private final Set<String> invalidUserNames;

    public UserHasMoreThatOneRoleException(Set<String> invalidUserNames) {
        this.invalidUserNames = invalidUserNames;
    }

    public Set<String> getInvalidUserNames() {
        return invalidUserNames;
    }
}