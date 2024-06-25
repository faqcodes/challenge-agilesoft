package com.faqcodes.agilesoft.users.application;

import org.springframework.security.crypto.password.PasswordEncoder;

import com.faqcodes.agilesoft.shared.application.UseCase;
import com.faqcodes.agilesoft.shared.models.MessageResult;
import com.faqcodes.agilesoft.shared.security.JwtTokenProvider;
import com.faqcodes.agilesoft.users.infrastructure.repositories.UserRepository;
import com.faqcodes.agilesoft.users.models.SigninRequest;
import com.faqcodes.agilesoft.users.models.SigninResponse;

public class SigninUseCase implements UseCase<SigninRequest, SigninResponse> {

  private final JwtTokenProvider jwtTokenProvider;
  private final PasswordEncoder passwordEncoder;
  private final UserRepository repository;

  public SigninUseCase(JwtTokenProvider jwtTokenProvider, PasswordEncoder passwordEncoder, UserRepository repository) {
    this.jwtTokenProvider = jwtTokenProvider;
    this.passwordEncoder = passwordEncoder;
    this.repository = repository;
  }

  @Override
  public MessageResult<SigninResponse> execute(SigninRequest requestModel) {
    // Fetch the user from the repository
    var user = repository.findById(requestModel.getUsername());

    // Verify the user's credentials
    if (!user.isPresent() || !passwordEncoder.matches(requestModel.getPassword(), user.get().getPassword())) {
      return new MessageResult<>("ERROR", "El nombre de usuario o la contraseña son incorrectos", null, null);
    }

    // Generate a new JWT token for the user
    final var token = jwtTokenProvider.createToken(requestModel.getUsername());

    // Create the response
    final var data = new SigninResponse(token);

    // Return the successful response
    return new MessageResult<>("SUCCESS", "Se ha iniciado sesión satisfactoriamente", null, data);
  }

}
