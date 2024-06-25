package com.faqcodes.agilesoft.users.configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.faqcodes.agilesoft.users.application.GetUseCase;
import com.faqcodes.agilesoft.shared.application.UseCase;
import com.faqcodes.agilesoft.users.infrastructure.repositories.UserRepository;
import com.faqcodes.agilesoft.users.models.GetRequest;
import com.faqcodes.agilesoft.users.models.GetResponse;

@Configuration
public class GetConfiguration {

  @Bean
  UseCase<GetRequest, GetResponse> getUserUseCase(UserRepository repository) {
    return new GetUseCase(repository);
  }

}
