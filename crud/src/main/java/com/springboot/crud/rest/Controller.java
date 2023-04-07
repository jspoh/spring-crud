package com.springboot.crud.rest;

import com.springboot.crud.jpaEntity.todos.Todo;
import com.springboot.crud.jpaEntity.todos.Todos;
import jakarta.annotation.PostConstruct;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    return todos.get(id);
  }
}
