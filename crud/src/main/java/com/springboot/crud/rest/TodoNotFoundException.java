package com.springboot.crud.rest;

public class TodoNotFoundException extends RuntimeException {
  public TodoNotFoundException(String message) {
    super(message);
  }

  public TodoNotFoundException(String message, Throwable cause) {
    super(message, cause);
  }

  public TodoNotFoundException(Throwable cause) {
    super(cause);
  }
}
