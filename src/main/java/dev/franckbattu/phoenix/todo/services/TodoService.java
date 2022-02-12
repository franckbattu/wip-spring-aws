package dev.franckbattu.phoenix.todo.services;

import dev.franckbattu.phoenix.dtos.TodoDto;
import dev.franckbattu.phoenix.models.Todo;

import java.util.List;
import java.util.Optional;

public interface TodoService {
  Optional<Todo> getTodo(Long todoId);
  Todo save(TodoDto source);
  List<Todo> findAllWithPagination(int page, int size);
}
