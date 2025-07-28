package com.glaulher.todolist.task;

import com.glaulher.todolist.utils.Utils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

  private static final String ID_USER = "idUser";

  private ITaskRepository taskRepository;

  public TaskController(ITaskRepository taskRepository) {
    this.taskRepository = taskRepository;
  }

  @PostMapping("/")
  public ResponseEntity<TaskModel> create(
      @Valid @RequestBody TaskModel taskModel, HttpServletRequest request) {

    taskModel.setIdUser((UUID) request.getAttribute(ID_USER));

    var currentDate = LocalDateTime.now();
    if (currentDate.isAfter(taskModel.getStartAt()) || currentDate.isAfter(taskModel.getEndAt())) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
    }
    if (taskModel.getStartAt().isAfter(taskModel.getEndAt())) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
    }

    return ResponseEntity.status(HttpStatus.CREATED).body(this.taskRepository.save(taskModel));
  }

  @GetMapping("/")
  public List<TaskModel> list(HttpServletRequest request) {
    var idUser = request.getAttribute(ID_USER);
    return this.taskRepository.findByIdUser((UUID) idUser);
  }

  @PutMapping("/{id}")
  public ResponseEntity<Object> update(
      @Valid @RequestBody TaskModel taskModel, HttpServletRequest request, @PathVariable UUID id) {

    var task = this.taskRepository.findById(id).orElse(null);
    var idUser = request.getAttribute(ID_USER);

    if (task == null) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND)
          .body(Map.of("message", "Tarefa nao encontrada."));
    }

    if (!task.getIdUser().equals(idUser)) {
      return ResponseEntity.status(HttpStatus.FORBIDDEN)
          .body(Map.of("message", "Você não tem permissão para editar esta tarefa."));
    }

    Utils.copyNonNullProperties(taskModel, task);

    return ResponseEntity.status(HttpStatus.OK).body(this.taskRepository.save(task));
  }
}
