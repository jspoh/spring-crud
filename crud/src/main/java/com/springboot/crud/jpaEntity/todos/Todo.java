package com.springboot.crud.jpaEntity.todos;

import jakarta.persistence.*;

@Entity
@Table(name="todos")  // table name
public class Todo {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name="id")
  private int id;

  @Column(name = "value")
  private String value;

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getValue() {
    return value;
  }

  public void setValue(String value) {
    this.value = value;
  }
  @Override
  public String toString() {
    return "Todos{" +
        "id=" + id +
        ", value='" + value + '\'' +
        '}';
  }
}
