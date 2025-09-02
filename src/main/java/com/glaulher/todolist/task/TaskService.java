package com.glaulher.todolist.task;

import com.glaulher.todolist.utils.Utils;
import jakarta.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class TaskService {

  private static final String MESSAGE = "message";

  private static final String ID_USER = "idUser";

  private ITaskRepository taskRepository;

  public TaskService(ITaskRepository taskRepository) {
    this.taskRepository = taskRepository;
  }

  public ResponseEntity<TaskModel> create(TaskModel taskModel, HttpServletRequest request) {

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

  public List<TaskModel> list(HttpServletRequest request) {
    var idUser = request.getAttribute(ID_USER);
    return this.taskRepository.findByIdUser((UUID) idUser);
  }

  public ResponseEntity<Object> update(TaskModel taskModel, HttpServletRequest request, UUID id) {

    var task = this.taskRepository.findById(id).orElse(null);
    var idUser = request.getAttribute(ID_USER);

    if (task == null) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND)
          .body(Map.of(MESSAGE, "Tarefa nao encontrada."));
    }

    if (!task.getIdUser().equals(idUser)) {
      return ResponseEntity.status(HttpStatus.FORBIDDEN)
          .body(Map.of(MESSAGE, "Você não tem permissão para editar esta tarefa."));
    }

    Utils.copyNonNullProperties(taskModel, task);

    return ResponseEntity.status(HttpStatus.OK).body(this.taskRepository.save(task));
  }

  public ResponseEntity<Object> delete(HttpServletRequest request, UUID id) {
    var task = this.taskRepository.findById(id).orElse(null);
    var idUser = request.getAttribute(ID_USER);

    if (task == null) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND)
          .body(Map.of(MESSAGE, "Tarefa não encontrada."));
    }

    if (!task.getIdUser().equals(idUser)) {
      return ResponseEntity.status(HttpStatus.FORBIDDEN)
          .body(Map.of(MESSAGE, "Você não tem permissão para excluir esta tarefa."));
    }

    this.taskRepository.delete(task);

    return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
  }
}
