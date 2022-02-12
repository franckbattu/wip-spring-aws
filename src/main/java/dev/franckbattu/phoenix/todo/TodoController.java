package dev.franckbattu.phoenix.todo;

import dev.franckbattu.phoenix.models.Todo;
import dev.franckbattu.phoenix.todo.services.TodoService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/v1/todos")
public class TodoController {

  private final TodoService todoService;

  @Autowired
  public TodoController(TodoService todoService) {
    this.todoService = todoService;
  }

  @GetMapping()
  public List<Todo> getTodos(
    @RequestParam(defaultValue = "0") int page,
    @RequestParam(defaultValue = "10") int size
  ) {
    return this.todoService.findAllWithPagination(page, size);
  }

  @GetMapping(value = "/{id}")
  public Todo getTodo(@PathVariable("id") Long id) throws EntityNotFoundException {
    return this.todoService.getTodo(id).orElseThrow(TodoNotFoundException::new);
  }
}
