package com.faqcodes.agilesoft.tasks.application;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.security.core.context.SecurityContextHolder;

import com.faqcodes.agilesoft.shared.application.UseCase;
import com.faqcodes.agilesoft.shared.models.MessageResult;
import com.faqcodes.agilesoft.tasks.infrastructure.repositories.TaskData;
import com.faqcodes.agilesoft.tasks.infrastructure.repositories.TaskRepository;
import com.faqcodes.agilesoft.tasks.models.CreateRequest;
import com.faqcodes.agilesoft.tasks.models.CreateResponse;
import com.faqcodes.agilesoft.users.application.GetUseCase;
import com.faqcodes.agilesoft.users.infrastructure.repositories.UserData;
import com.faqcodes.agilesoft.users.infrastructure.repositories.UserRepository;
import com.faqcodes.agilesoft.users.models.GetRequest;
import com.faqcodes.agilesoft.users.models.GetResponse;

public class CreateUseCase implements UseCase<CreateRequest, CreateResponse> {

  private final UseCase<GetRequest, GetResponse> getUserUseCase;
  private final TaskRepository repository;

  public CreateUseCase(UseCase<GetRequest, GetResponse> getUserUseCase, TaskRepository repository) {
    this.getUserUseCase = getUserUseCase;
    this.repository = repository;
  }

  @Override
  public MessageResult<CreateResponse> execute(CreateRequest requestModel) {
    // Fetch the user from the repository
    final var request = GetRequest.builder().username(requestModel.getUsername()).build();
    final var response = getUserUseCase.execute(request);

    if (response.getCode().equals("ERROR")) {
      return new MessageResult<>("ERROR", response.getMessage(), null, null);
    }

    final var user = response.getData();

    // Create the UserData
    final var userData = UserData.builder()
        .username(user.getUsername())
        .name(user.getName())
        .build();

    // Create the TaskData
    final var taskId = UUID.randomUUID();
    final var task = TaskData.builder()
        .taskId(taskId)
        .name(requestModel.getName())
        .description(requestModel.getDescription())
        .createdAt(LocalDateTime.now())
        .updatedAt(LocalDateTime.now())
        .status(false)
        .user(userData)
        .build();

    System.out.println(task);

    final var result = repository.save(task);
    System.out.println(result);

    // // Obtener la tarea desde el resultado
    // final var newTask = result.getTasks().stream()
    // .filter(t -> taskId.equals(t.getTaskId())) // Puedes ajustar el filtro según
    // tus necesidades
    // .findFirst()
    // .orElseThrow(() -> new RuntimeException("La tarea guardada no se encontró en
    // el usuario actual"));

    var data = CreateResponse.builder()
        .taskId(result.getTaskId().toString())
        .name(result.getName())
        .description(result.getDescription())
        .createdAt(result.getCreatedAt().toString())
        .updatedAt(result.getUpdatedAt().toString())
        .status(result.isStatus())
        .build();

    // return success information
    return new MessageResult<>("SUCCESS", "La tarea se ha creado satisfactoriamente", null, data);
  }

}
