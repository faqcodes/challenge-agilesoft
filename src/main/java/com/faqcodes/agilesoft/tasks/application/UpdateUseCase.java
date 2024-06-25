package com.faqcodes.agilesoft.tasks.application;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.security.core.context.SecurityContextHolder;

import com.faqcodes.agilesoft.shared.application.UseCase;
import com.faqcodes.agilesoft.shared.models.MessageResult;
import com.faqcodes.agilesoft.tasks.infrastructure.repositories.TaskRepository;
import com.faqcodes.agilesoft.tasks.models.UpdateRequest;
import com.faqcodes.agilesoft.tasks.models.UpdateResponse;

public class UpdateUseCase implements UseCase<UpdateRequest, UpdateResponse> {

  private final TaskRepository repository;

  public UpdateUseCase(TaskRepository repository) {
    this.repository = repository;
  }

  @Override
  public MessageResult<UpdateResponse> execute(UpdateRequest requestModel) {
    final var authentication = SecurityContextHolder.getContext().getAuthentication();

    if (!authentication.isAuthenticated()) {
      return new MessageResult<>("ERROR", "No se pudo autenticar el usuario", null, null);
    }

    // Fetch the user from the repository
    final var taskId = UUID.fromString(requestModel.getTaskId());
    final var taskData = repository.findById(taskId);

    if (!taskData.isPresent()) {
      return new MessageResult<>("ERROR", "La tarea no existe", null, null);
    }

    final var task = taskData.get();

    task.setStatus(requestModel.isStatus());
    task.setUpdatedAt(LocalDateTime.now());

    final var result = repository.save(task);

    var data = UpdateResponse.builder()
        .taskId(result.getTaskId().toString())
        .name(result.getName())
        .description(result.getDescription())
        .createdAt(result.getCreatedAt().toString())
        .updatedAt(result.getUpdatedAt().toString())
        .status(result.isStatus())
        .build();

    // return success information
    return new MessageResult<>("SUCCESS", "La tarea se ha actualizado satisfactoriamente", null, data);
  }

}
