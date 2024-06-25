package com.faqcodes.agilesoft.tasks.infrastructure.controllers;

import java.net.URI;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.faqcodes.agilesoft.shared.application.UseCase;
import com.faqcodes.agilesoft.tasks.models.UpdateRequest;
import com.faqcodes.agilesoft.tasks.models.UpdateResponse;

import jakarta.validation.Valid;

@Validated
@RestController
@RequestMapping("/api/tasks")
public class UpdateController {

  UseCase<UpdateRequest, UpdateResponse> updateUseCase;

  public UpdateController(UseCase<UpdateRequest, UpdateResponse> updateUseCase) {
    this.updateUseCase = updateUseCase;
  }

  @PutMapping
  public ResponseEntity<?> update(@Valid @RequestBody UpdateRequest request) {

    var response = updateUseCase.execute(request);

    if (response.getCode().equals("ERROR")) {
      return ResponseEntity.badRequest().body(response);
    }

    return ResponseEntity.ok(response);
  }
}
