package dev.franckbattu.phoenix.todo.services;

import dev.franckbattu.phoenix.dtos.TodoDto;
import dev.franckbattu.phoenix.models.Todo;
import dev.franckbattu.phoenix.todo.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DefaultTodoService implements TodoService {

  private final TodoRepository todoRepository;

  @Autowired
  protected DefaultTodoService(TodoRepository todoRepository) {
    this.todoRepository = todoRepository;
  }

  @Override
  public Optional<Todo> getTodo(Long todoId) {
    return this.todoRepository.findById(todoId);
  }

  @Override
  public Todo save(TodoDto source) {
    var todo = Todo.of(source.name());
    return this.todoRepository.save(todo);
  }

  @Override
  public List<Todo> findAllWithPagination(int page, int size) {
    var paging = PageRequest.of(page, size);
    var result = this.todoRepository.findAll(paging);
    return result.toList();
  }
}
