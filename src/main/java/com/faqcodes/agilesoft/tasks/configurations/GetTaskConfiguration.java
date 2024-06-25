package com.faqcodes.agilesoft.tasks.configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.faqcodes.agilesoft.shared.application.UseCase;
import com.faqcodes.agilesoft.tasks.application.GetTaskUseCase;
import com.faqcodes.agilesoft.tasks.infrastructure.repositories.TaskRepository;
import com.faqcodes.agilesoft.tasks.models.GetTaskRequest;
import com.faqcodes.agilesoft.tasks.models.GetTaskResponse;
import com.faqcodes.agilesoft.users.models.GetRequest;
import com.faqcodes.agilesoft.users.models.GetResponse;

@Configuration
public class GetTaskConfiguration {

  @Bean
  UseCase<GetTaskRequest, GetTaskResponse> getTaskUseCase(
      UseCase<GetRequest, GetResponse> getUserUseCase,
      TaskRepository repository) {
    return new GetTaskUseCase(getUserUseCase, repository);
  }

}
