package com.faqcodes.agilesoft.tasks.infrastructure.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.faqcodes.agilesoft.shared.application.UseCase;
import com.faqcodes.agilesoft.tasks.models.GetTaskRequest;
import com.faqcodes.agilesoft.tasks.models.GetTaskResponse;

@Validated
@RestController
@RequestMapping("/api/tasks")
public class GetTaskController {

  UseCase<GetTaskRequest, GetTaskResponse> getTaskUseCase;

  public GetTaskController(UseCase<GetTaskRequest, GetTaskResponse> getTaskUseCase) {
    this.getTaskUseCase = getTaskUseCase;
  }

  @GetMapping
  public ResponseEntity<?> get() {
    final var authentication = SecurityContextHolder.getContext().getAuthentication();

    if (!authentication.isAuthenticated()) {
      return ResponseEntity.status(401).build();
    }

    final var username = authentication.getPrincipal().toString();
    final var request = GetTaskRequest.builder().username(username).build();

    System.out.println(request);

    var response = getTaskUseCase.execute(request);

    if (response.getCode().equals("ERROR")) {
      return ResponseEntity.badRequest().body(response);
    }

    return ResponseEntity.ok(response);
  }
}
