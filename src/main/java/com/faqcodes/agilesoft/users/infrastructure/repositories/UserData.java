package com.faqcodes.agilesoft.users.infrastructure.repositories;

import java.util.ArrayList;
import java.util.List;

import com.faqcodes.agilesoft.tasks.infrastructure.repositories.TaskData;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OrderColumn;
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
@Table(name = "users")
public class UserData {
  @Id
  @Column(name = "username")
  private String username;

  @Column(name = "password")
  private String password;

  @Column(name = "name")
  private String name;

  @OrderColumn(name = "tasks")
  @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
  private List<TaskData> tasks = new ArrayList<>();

  public void addTask(TaskData task) {
    this.tasks.add(task);
  }
}