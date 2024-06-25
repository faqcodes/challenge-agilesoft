package com.faqcodes.agilesoft.users.application;

import java.util.stream.Collectors;

import com.faqcodes.agilesoft.shared.application.UseCase;
import com.faqcodes.agilesoft.shared.models.MessageResult;
import com.faqcodes.agilesoft.tasks.domain.TaskEntity;
import com.faqcodes.agilesoft.tasks.infrastructure.repositories.TaskData;
import com.faqcodes.agilesoft.users.infrastructure.repositories.UserRepository;
import com.faqcodes.agilesoft.users.models.GetRequest;
import com.faqcodes.agilesoft.users.models.GetResponse;

public class GetUseCase implements UseCase<GetRequest, GetResponse> {

  private final UserRepository repository;

  public GetUseCase(UserRepository repository) {
    this.repository = repository;
  }

  @Override
  public MessageResult<GetResponse> execute(GetRequest requestModel) {
    // Fetch the user from the repository
    final var userData = repository.findById(requestModel.getUsername());

    // Verify the user's credentials
    if (!userData.isPresent()) {
      return new MessageResult<>("ERROR", "El usuario no existe", null, null);
    }

    final var user = userData.get();

    // Create output Data
    final var data = GetResponse.builder()
        .name(user.getName())
        .username(user.getUsername())
        .build();

    // Return success information
    return new MessageResult<>("SUCCESS", "El usuario se ha obtenido satisfactoriamente", null, data);
  }

}
