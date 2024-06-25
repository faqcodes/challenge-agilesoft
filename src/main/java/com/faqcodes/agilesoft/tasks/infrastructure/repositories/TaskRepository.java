package com.faqcodes.agilesoft.tasks.infrastructure.repositories;

import java.util.UUID;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<TaskData, UUID> {
  List<TaskData> findByUsername(String username);
}