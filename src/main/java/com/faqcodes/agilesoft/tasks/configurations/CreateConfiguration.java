package com.faqcodes.agilesoft.tasks.configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.faqcodes.agilesoft.shared.application.UseCase;
import com.faqcodes.agilesoft.tasks.application.CreateUseCase;
import com.faqcodes.agilesoft.tasks.infrastructure.repositories.TaskRepository;
import com.faqcodes.agilesoft.tasks.models.CreateRequest;
import com.faqcodes.agilesoft.tasks.models.CreateResponse;
import com.faqcodes.agilesoft.users.application.GetUseCase;
import com.faqcodes.agilesoft.users.infrastructure.repositories.UserRepository;
import com.faqcodes.agilesoft.users.models.GetRequest;
import com.faqcodes.agilesoft.users.models.GetResponse;

@Configuration
public class CreateConfiguration {

  @Bean
  UseCase<CreateRequest, CreateResponse> createUseCase(
      UseCase<GetRequest, GetResponse> getUserUseCase,
      TaskRepository repository) {
    return new CreateUseCase(getUserUseCase, repository);
  }

}
