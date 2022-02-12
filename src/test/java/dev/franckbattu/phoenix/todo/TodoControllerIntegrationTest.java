package dev.franckbattu.phoenix.todo;

import dev.franckbattu.phoenix.models.Todo;
import dev.franckbattu.phoenix.todo.services.TodoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
public class TodoControllerIntegrationTest {

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private TodoService todoService;

  @Test
  void getTodos() throws Exception {
    var todos = Stream.of(
      new Todo(1L, "Todo 1", false),
      new Todo(2L, "Todo 2", false)
    ).toList();
    when(this.todoService.findAllWithPagination(0, 10)).thenReturn(todos);
    this.mockMvc
      .perform(get("/v1/todos"))
      .andExpect(status().isOk())
      .andExpect(jsonPath("$.length()").value(2))
      .andExpect(jsonPath("$[0].name").value("Todo 1"))
      .andExpect(jsonPath("$[1].name").value("Todo 2"));
  }

  @Test
  void getTodosWithCustomPagination() throws Exception {
    var todos = Stream.of(
      new Todo(1L, "Todo 1", false)
    ).toList();
    when(this.todoService.findAllWithPagination(0, 1)).thenReturn(todos);
    this.mockMvc
      .perform(get("/v1/todos?size=1"))
      .andExpect(status().isOk())
      .andExpect(jsonPath("$.length()").value(1))
      .andExpect(jsonPath("$[0].name").value("Todo 1"));
  }

  @Test
  void getTodoThrowsTodoNotFoundException() throws Exception {
    when(this.todoService.getTodo(2L)).thenThrow(TodoNotFoundException.class);
    this.mockMvc
      .perform(get("/v1/todos/2"))
      .andExpect(status().isNotFound())
      .andExpect(result -> assertTrue(result.getResolvedException() instanceof TodoNotFoundException));
  }
}
