package com.springboot.crud.jpaEntity.todos;

import java.util.List;

public interface TodoDAO {
  void addTodo(Todo item);
  List<Todo> getAllTodos();
  Todo updateTodo(Integer id, Todo item);
  void deleteTodo(Integer id);
}
