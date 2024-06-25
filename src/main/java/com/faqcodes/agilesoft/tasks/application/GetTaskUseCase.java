package com.faqcodes.agilesoft.tasks.application;

import java.util.stream.Collectors;

import com.faqcodes.agilesoft.shared.application.UseCase;
import com.faqcodes.agilesoft.shared.models.MessageResult;
import com.faqcodes.agilesoft.tasks.domain.TaskEntity;
import com.faqcodes.agilesoft.tasks.infrastructure.repositories.TaskData;
import com.faqcodes.agilesoft.tasks.infrastructure.repositories.TaskRepository;
import com.faqcodes.agilesoft.tasks.models.GetTaskRequest;
import com.faqcodes.agilesoft.tasks.models.GetTaskResponse;
import com.faqcodes.agilesoft.users.models.GetRequest;
import com.faqcodes.agilesoft.users.models.GetResponse;

public class GetTaskUseCase implements UseCase<GetTaskRequest, GetTaskResponse> {

  private final UseCase<GetRequest, GetResponse> getUserUseCase;
  private final TaskRepository repository;

  public GetTaskUseCase(UseCase<GetRequest, GetResponse> getUserUseCase, TaskRepository repository) {
    this.getUserUseCase = getUserUseCase;
    this.repository = repository;
  }

  @Override
  public MessageResult<GetTaskResponse> execute(GetTaskRequest requestModel) {
    // Fetch the user from the repository
    final var request = GetRequest.builder().username(requestModel.getUsername()).build();
    final var response = getUserUseCase.execute(request);

    if (response.getCode().equals("ERROR")) {
      return new MessageResult<>("ERROR", response.getMessage(), null, null);
    }

    // Fetch the tasks from the repository using the username from the response
    final var tasks = repository.findByUsername(response.getData().getUsername());

    System.out.println(tasks);

    var data = GetTaskResponse.builder()
        .tasks(tasks
            .stream()
            .map(TaskMapper::toTaskEntity)
            .collect(Collectors.toList()))
        .build();

    // return success information
    return new MessageResult<>("SUCCESS", "Las tareas se han obtenido satisfactoriamente", null, data);
  }

  private class TaskMapper {
    public static TaskEntity toTaskEntity(TaskData taskData) {
      return TaskEntity.builder()
          .taskId(taskData.getTaskId().toString())
          .name(taskData.getName())
          .description(taskData.getDescription())
          .createdAt(taskData.getCreatedAt())
          .updatedAt(taskData.getUpdatedAt())
          .status(taskData.isStatus())
          .build();
    }
  }
}
