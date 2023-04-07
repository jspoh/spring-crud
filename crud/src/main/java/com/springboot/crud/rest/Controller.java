package com.springboot.crud.rest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.springboot.crud.jpaEntity.todos.Todo;
import com.springboot.crud.jpaEntity.todos.TodoDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class Controller {
  @GetMapping("/")
  public String getTodos() {
    return "zz";
  }
}
