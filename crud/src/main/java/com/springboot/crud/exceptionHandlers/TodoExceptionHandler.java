package com.springboot.crud.exceptionHandlers;

import com.springboot.crud.exceptionHandlers.responses.TodoErrorResponse;
import com.springboot.crud.exceptionHandlers.exceptions.TodoNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class TodoExceptionHandler {
  // handle exceptions of type TodoNotFoundException.
  @ExceptionHandler
  public ResponseEntity<TodoErrorResponse> handleException(TodoNotFoundException err) {
    TodoErrorResponse res = new TodoErrorResponse(HttpStatus.NOT_FOUND.value(), err.getMessage(), System.currentTimeMillis());
//    res.setStatus(HttpStatus.NOT_FOUND.value());
//    res.setMessage(err.getMessage());
//    res.setTimeStamp(System.currentTimeMillis());

    return new ResponseEntity<>(res, HttpStatus.NOT_FOUND);
  }

  // handle all other exceptions (see parameter of Exception type catches all exceptions)
  @ExceptionHandler
  public ResponseEntity<TodoErrorResponse> handleException(Exception err) {
    TodoErrorResponse res = new TodoErrorResponse(HttpStatus.BAD_REQUEST.value(), err.getMessage(), System.currentTimeMillis());

    return new ResponseEntity<>(res, HttpStatus.BAD_REQUEST);
  }
}
