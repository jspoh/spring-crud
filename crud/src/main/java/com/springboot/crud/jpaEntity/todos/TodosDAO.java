package com.springboot.crud.jpaEntity.todos;

import java.util.List;

public interface TodosDAO {
  void addTodo(Todos item);
  Todos getTodo(Integer id);
  List<Todos> getAllTodos();
  Todos updateTodo(Integer id, Todos item);
  void deleteTodo(Integer id);
}
