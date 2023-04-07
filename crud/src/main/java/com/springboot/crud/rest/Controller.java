package com.springboot.crud.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {
  @GetMapping("/")
  public String getTodos() {
    return "wait";
  }
}
