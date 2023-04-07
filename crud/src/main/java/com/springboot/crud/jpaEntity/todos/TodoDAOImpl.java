package com.springboot.crud.jpaEntity.todos;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public class TodoDAOImpl implements TodoDAO{
  private final EntityManager entityManager;

  @Autowired
  public TodoDAOImpl(EntityManager entityManager) {
    this.entityManager = entityManager;
  }


  @Override
  public void addTodo(Todo item) {
    entityManager.persist(item);
  }

  @Override
  public List<Todo> getAllTodos() {
    TypedQuery<Todo> query = entityManager.createQuery("from todos", Todo.class);
    List<Todo> todos = query.getResultList();
    return todos;
  }

  @Override
  @Transactional
  public Todo updateTodo(Integer id, Todo item) {
    Todo todo = entityManager.find(Todo.class, id);
    todo.setValue(item.getValue());

    entityManager.merge(todo);

    return todo;
  }

  @Override
  @Transactional
  public void deleteTodo(Integer id) {
    Todo todo = entityManager.find(Todo.class, id);
    entityManager.remove(todo);
  }
}
