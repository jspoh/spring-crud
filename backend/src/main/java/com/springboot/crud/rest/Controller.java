package com.springboot.crud.rest;

import com.springboot.crud.database.entities.Todos;
import com.springboot.crud.database.dao.TodosDAO;
import com.springboot.crud.exceptionHandlers.exceptions.TodoNotFoundException;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000", allowedHeaders = "*")
@RestController
@RequestMapping("/api")
public class Controller {

  private TodosDAO todosDAO;
  private List<Todos> todos;

  @Autowired
  public Controller(TodosDAO todosDAO) {
    this.todosDAO = todosDAO;
  }

  @PostConstruct
  public void loadData() {
    this.todos = this.todosDAO.getAllTodos();
  }

  @GetMapping("/todos")
  public List<Todos> getTodos() {
    return this.todos;
  }

  @GetMapping("/todos/{id}")
  public Todos getTodo(@PathVariable Integer id) {
    if (id >= this.todos.size() || id < 0) {
      throw new TodoNotFoundException("Todo can not be found - " + id);
    }

    Todos todo = this.todosDAO.getTodo(id);

    return todo;
  }

  @PostMapping("/todos")
  public void addTodo(@RequestBody Todos todo) {
    this.todosDAO.addTodo(todo);
    this.loadData();
  }

  @PutMapping("/todos")
  public void updateTodo(@RequestBody Todos todo) {
    this.todosDAO.updateTodo(todo);
    this.loadData();
  }

  @DeleteMapping("/todos/{id}")
  public void deleteTodo(@PathVariable Integer id) {
    this.todosDAO.deleteTodo(id);
    this.loadData();
  }
}
