package com.faqcodes.agilesoft.tasks.infrastructure.repositories;

import java.time.LocalDateTime;
import java.util.UUID;

import com.faqcodes.agilesoft.users.infrastructure.repositories.UserData;
import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OrderColumn;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tasks")
public class TaskData {
  @Id
  @Column(name = "taskId")
  private UUID taskId;

  @Column(name = "name")
  private String name;

  @Column(name = "description")
  private String description;

  @Column(name = "created_at")
  private LocalDateTime createdAt;

  @Column(name = "updated_at")
  private LocalDateTime updatedAt;

  @Column(name = "status")
  private boolean status;

  @Column(name = "username")
  private String username;

  // @ManyToOne
  // @JoinColumn(name = "username", nullable = false)
  // private UserData user;
}