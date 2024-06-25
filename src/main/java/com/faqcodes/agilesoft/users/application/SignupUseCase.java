package com.faqcodes.agilesoft.users.application;

import com.faqcodes.agilesoft.shared.application.UseCase;
import com.faqcodes.agilesoft.shared.models.MessageResult;
import com.faqcodes.agilesoft.users.infrastructure.repositories.UserData;
import com.faqcodes.agilesoft.users.infrastructure.repositories.UserRepository;
import com.faqcodes.agilesoft.users.models.SignupRequest;
import com.faqcodes.agilesoft.users.models.SignupResponse;

public class SignupUseCase implements UseCase<SignupRequest, SignupResponse> {

  private final UserRepository repository;

  public SignupUseCase(UserRepository repository) {
    this.repository = repository;
  }

  @Override
  public MessageResult<SignupResponse> execute(SignupRequest requestModel) {
    // Fetch the user from the repository
    var user = repository.findById(requestModel.getUsername());

    // Verify the user's credentials
    if (user.isPresent()) {
      return new MessageResult<>("ERROR", "El usuario ya existe", null, null);
    }

    var userdata = UserData.builder()
        .username(requestModel.getUsername())
        .password(requestModel.getPassword())
        .name(requestModel.getName())
        .build();

    var result = repository.save(userdata);

    // Create output Data
    final var data = new SignupResponse(result.getName(), result.getUsername());

    // Return success information
    return new MessageResult<>("SUCCESS", "El usuario se ha creado satisfactoriamente", null, data);
  }

}
