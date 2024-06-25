package com.faqcodes.agilesoft.users.configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.faqcodes.agilesoft.shared.application.UseCase;
import com.faqcodes.agilesoft.users.application.SignupUseCase;
import com.faqcodes.agilesoft.users.infrastructure.repositories.UserRepository;
import com.faqcodes.agilesoft.users.models.SignupRequest;
import com.faqcodes.agilesoft.users.models.SignupResponse;

@Configuration
public class SignupConfiguration {

  @Bean
  UseCase<SignupRequest, SignupResponse> signupUseCase(
      UserRepository repository) {
    return new SignupUseCase(repository);
  }

}
