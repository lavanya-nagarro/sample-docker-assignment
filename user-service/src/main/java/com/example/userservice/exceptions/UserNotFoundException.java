package com.example.userservice.exceptions;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class UserNotFoundException extends Throwable {
    public UserNotFoundException(String id) {

    }
}
