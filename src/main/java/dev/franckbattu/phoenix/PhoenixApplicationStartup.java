package dev.franckbattu.phoenix;

import dev.franckbattu.phoenix.dtos.TodoDto;
import dev.franckbattu.phoenix.todo.services.TodoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.stream.Stream;

@Component
@Slf4j
public class PhoenixApplicationStartup implements CommandLineRunner {

  private final TodoService todoService;

  @Autowired
  public PhoenixApplicationStartup(TodoService todoService) {
    this.todoService = todoService;
  }

  @Override
  public void run(String... args) throws Exception {
    log.info("Database initialization");
    Stream.of("Todo 1", "Todo 2", "Todo 3").map(TodoDto::new).forEach(this.todoService::save);
    log.info("Database populated");
  }
}
