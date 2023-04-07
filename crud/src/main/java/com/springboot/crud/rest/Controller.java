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
}
