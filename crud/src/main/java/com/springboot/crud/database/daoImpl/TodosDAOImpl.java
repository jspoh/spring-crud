package com.springboot.crud.database.daoImpl;

import com.springboot.crud.database.dao.TodosDAO;
import com.springboot.crud.database.entities.Todos;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class TodosDAOImpl implements TodosDAO {
  private final EntityManager entityManager;

  @Autowired
  public TodosDAOImpl(EntityManager entityManager) {
    this.entityManager = entityManager;
  }

  @Override
  @Transactional
  public void addTodo(Todos item) {
    entityManager.persist(item);
  }

  @Override
  public Todos getTodo(Integer id) {
    TypedQuery<Todos> query = entityManager.createQuery("from Todos where id=" + id, Todos.class);
    Todos todo = query.getSingleResult();
    return todo;
  }

  @Override
  public List<Todos> getAllTodos() {
    TypedQuery<Todos> query = entityManager.createQuery("from Todos", Todos.class);
    List<Todos> todos = query.getResultList();
    return todos;
  }

  @Override
  @Transactional
  public void updateTodo(Todos item) {
    Todos todo = entityManager.find(Todos.class, item.getId());
    todo.setValue(item.getValue());

    entityManager.merge(todo);
  }

  @Override
  @Transactional
  public void deleteTodo(Integer id) {
    Todos todo = entityManager.find(Todos.class, id);
    entityManager.remove(todo);
  }
}
