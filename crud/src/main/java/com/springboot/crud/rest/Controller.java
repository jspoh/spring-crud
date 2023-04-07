package com.springboot.crud.rest;

import com.springboot.crud.jpaEntity.todos.Todo;
import com.springboot.crud.jpaEntity.todos.Todos;
import jakarta.annotation.PostConstruct;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class Controller {
  private List<Todo> todos;

  @PostConstruct  // kinda like onInit
  public void loadData() {
    this.todos = new ArrayList<>();
    this.todos.add(new Todo(1, "value1"));
    this.todos.add(new Todo(2, "value2"));
    this.todos.add(new Todo(3, "value3"));
  }

  @GetMapping("/todos")
  public List<Todo> getTodos() {
    return this.todos;
  }

  @GetMapping("/todo/{id}")
  public Todo getTodo(@PathVariable Integer id) {
    if (id >= this.todos.size() || id < 0) {
      throw new TodoNotFoundException("Todo can not be found - " + id);
    }

    return todos.get(id);
  }

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
