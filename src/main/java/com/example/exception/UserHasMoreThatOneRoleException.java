package com.example.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.Set;

@ResponseStatus(value= HttpStatus.EXPECTATION_FAILED, reason="User should not have more that one role")
public class UserHasMoreThatOneRoleException extends RuntimeException {
    private Set<String> faildUserRights;

    public UserHasMoreThatOneRoleException(Set<String> faildUserRights) {
        this.faildUserRights = faildUserRights;
    }

    public Set<String> getFaildUserRights() {
        return faildUserRights;
    }
}