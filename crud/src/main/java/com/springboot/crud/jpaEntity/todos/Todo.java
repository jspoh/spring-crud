package com.springboot.crud.jpaEntity.todos;

import org.springframework.beans.factory.annotation.Autowired;

public class Todo {
  private Integer id;
  private String value;

  @Autowired
  public Todo(Integer id, String value) {
    this.id = id;
    this.value = value;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getValue() {
    return value;
  }

  public void setValue(String value) {
    this.value = value;
  }
}
