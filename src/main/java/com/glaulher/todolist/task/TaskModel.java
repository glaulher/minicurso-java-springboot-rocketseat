package com.glaulher.todolist.task;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.UUID;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

@Data
@Entity(name = "tb_tasks")
public class TaskModel {

  @Id
  @GeneratedValue(generator = "UUID")
  private UUID id;

  private String description;

  @Column(length = 50)
  @Size(max = 50, message = "O campo título deve ter no máximo 50 caracteres.")
  private String title;

  private LocalDateTime startAt;
  private LocalDateTime endAt;
  private String priority;
  private UUID idUser;

  @CreationTimestamp private LocalDateTime createdAt;
}
