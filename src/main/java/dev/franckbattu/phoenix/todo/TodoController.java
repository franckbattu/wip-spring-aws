package dev.franckbattu.phoenix.todo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Stream;

@RestController
@RequestMapping(value = "/v1/todos")
public class TodoController {

  record Todo(String name) {}

  @GetMapping()
  public List<Todo> getTodos() {
    return Stream.of("Todo 1", "Todo 2", "Todo 3").map(Todo::new).toList();
  }
}
