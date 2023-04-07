package com.springboot.crud.jpaEntity.todos;

import java.util.List;

public interface TodosDAO {
  void addTodo(Todo item);
  Todo getTodo(Integer id);
  List<Todo> getAllTodos();
  Todo updateTodo(Integer id, Todo item);
  void deleteTodo(Integer id);
}
