package com.faqcodes.agilesoft.tasks.configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.faqcodes.agilesoft.shared.application.UseCase;
import com.faqcodes.agilesoft.tasks.application.UpdateUseCase;
import com.faqcodes.agilesoft.tasks.infrastructure.repositories.TaskRepository;
import com.faqcodes.agilesoft.tasks.models.UpdateRequest;
import com.faqcodes.agilesoft.tasks.models.UpdateResponse;

@Configuration
public class UpdateConfiguration {

  @Bean
  UseCase<UpdateRequest, UpdateResponse> updateUseCase(TaskRepository repository) {
    return new UpdateUseCase(repository);
  }

}
