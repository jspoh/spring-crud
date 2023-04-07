package com.springboot.crud.rest;

import com.springboot.crud.jpaEntity.todos.Todos;
import com.springboot.crud.jpaEntity.todos.TodosDAO;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class Controller {

  private final TodosDAO todosDAO;
  private List<Todos> todos;

  @Autowired
  public Controller(TodosDAO todosDAO) {
    this.todosDAO = todosDAO;
  }

  @PostConstruct
  public void loadData() {
    this.todos = this.todosDAO.getAllTodos();
  }

  /*
  @PostConstruct  // kinda like onInit
  public void loadData() {
    this.todos = new ArrayList<>();
    this.todos.add(new Todo(1, "value1"));
    this.todos.add(new Todo(2, "value2"));
    this.todos.add(new Todo(3, "value3"));
  }
   */

  @GetMapping("/todos")
  public List<Todos> getTodos() {
    return this.todos;
  }

  @GetMapping("/todos/{id}")
  public Todos getTodo(@PathVariable Integer id) {
    if (id >= this.todos.size() || id < 0) {
      throw new TodoNotFoundException("Todo can not be found - " + id);
    }

    return this.todos.get(id);
  }
}
