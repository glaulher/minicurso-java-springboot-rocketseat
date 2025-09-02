package com.glaulher.todolist.task;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import java.util.List;
import java.util.UUID;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tasks")
public class TaskController {

  private final TaskService taskService;

  public TaskController(TaskService taskService) {
    this.taskService = taskService;
  }

  @PostMapping("/")
  public ResponseEntity<TaskModel> create(
      @Valid @RequestBody TaskModel taskModel, HttpServletRequest request) {
    return taskService.create(taskModel, request);
  }

  @GetMapping("/")
  public List<TaskModel> list(HttpServletRequest request) {
    return taskService.list(request);
  }

  @PutMapping("/{id}")
  public ResponseEntity<Object> update(
      @Valid @RequestBody TaskModel taskModel, HttpServletRequest request, @PathVariable UUID id) {

    return taskService.update(taskModel, request, id);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Object> delete(HttpServletRequest request, @PathVariable UUID id) {

    return taskService.delete(request, id);
  }
}
