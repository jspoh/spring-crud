package com.springboot.crud.impl;

import com.springboot.crud.interfaces.Todo;
import org.springframework.beans.factory.annotation.Autowired;

public class TodoImpl implements Todo {
  private final Integer id;
  private final String value;

  @Autowired
  public TodoImpl(Integer id, String value) {
    this.id = id;
    this.value = value;
  }

  @Override
  public Integer getId() {
    return this.id;
  }

  @Override
  public String getValue() {
    return this.value;
  }
}
