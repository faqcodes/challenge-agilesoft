package com.faqcodes.agilesoft.tasks.infrastructure.controllers;

import java.net.URI;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.faqcodes.agilesoft.shared.application.UseCase;
import com.faqcodes.agilesoft.shared.models.MessageResult;
import com.faqcodes.agilesoft.tasks.models.CreateRequest;
import com.faqcodes.agilesoft.tasks.models.CreateResponse;

import jakarta.validation.Valid;

@Validated
@RestController
@RequestMapping("/api/tasks")
public class CreateController {

  UseCase<CreateRequest, CreateResponse> createUseCase;

  public CreateController(UseCase<CreateRequest, CreateResponse> createUseCase) {
    this.createUseCase = createUseCase;
  }

  @PostMapping
  public ResponseEntity<?> create(@Valid @RequestBody CreateRequest request) {
    final var authentication = SecurityContextHolder.getContext().getAuthentication();

    if (!authentication.isAuthenticated()) {
      return ResponseEntity.status(401).build();
    }

    request.setUsername(authentication.getPrincipal().toString());

    System.out.println(request);

    var response = createUseCase.execute(request);

    if (response.getCode().equals("ERROR")) {
      return ResponseEntity.badRequest().body(response);
    }

    return ResponseEntity.created(URI.create("")).body(response);
  }
}
