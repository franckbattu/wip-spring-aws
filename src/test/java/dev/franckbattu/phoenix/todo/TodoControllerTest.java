package dev.franckbattu.phoenix.todo;

import dev.franckbattu.phoenix.models.Todo;
import dev.franckbattu.phoenix.todo.services.TodoService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
public class TodoControllerTest {

  private final TodoService todoService;

  public TodoControllerTest() {
    this.todoService = Mockito.mock(TodoService.class);
  }

  @Test
  void shouldGetTodo() {
    var todo = new Todo(1L, "Todo 1", false);
    when(this.todoService.getTodo(1L)).thenReturn(Optional.of(todo));
    var todoController = new TodoController(this.todoService);
    assertEquals(todo, todoController.getTodo(1L));
  }

}
