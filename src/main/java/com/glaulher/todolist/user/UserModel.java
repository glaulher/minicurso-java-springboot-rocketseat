package com.glaulher.todolist.user;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import java.time.LocalDateTime;
import java.util.UUID;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

@Data
@Entity(name = "tb_users")
public class UserModel {

  @Id
  @GeneratedValue(generator = "UUID")
  @Column(name = "id", nullable = false)
  private UUID id;

  @Column(unique = true)
  private String username;

  private String name;
  private String password;

  @CreationTimestamp private LocalDateTime createdAt;
}
