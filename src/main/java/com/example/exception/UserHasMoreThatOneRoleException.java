package com.example.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.EXPECTATION_FAILED, reason="User should not have more that one role")
public class UserHasMoreThatOneRoleException extends RuntimeException {
}
