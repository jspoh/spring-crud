package com.springboot.crud.database.dao;

import com.springboot.crud.database.entities.Todos;

import java.util.List;

public interface TodosDAO {
  void addTodo(Todos item);
  Todos getTodo(Integer id);
  List<Todos> getAllTodos();
  Todos updateTodo(Integer id, Todos item);
  void deleteTodo(Integer id);
}
