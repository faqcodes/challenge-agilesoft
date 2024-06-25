package com.faqcodes.agilesoft.users.infrastructure.controllers;

import java.net.URI;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.faqcodes.agilesoft.shared.application.UseCase;
import com.faqcodes.agilesoft.users.models.SignupRequest;
import com.faqcodes.agilesoft.users.models.SignupResponse;

import jakarta.validation.Valid;

@Validated
@RestController
// @CrossOrigin(origins = "*")
@RequestMapping("/api/users")
public class SignupController {

  UseCase<SignupRequest, SignupResponse> signupUseCase;

  public SignupController(UseCase<SignupRequest, SignupResponse> signupUseCase) {
    this.signupUseCase = signupUseCase;
  }

  @PostMapping("/signup")
  public ResponseEntity<?> signup(@Valid @RequestBody SignupRequest signupRequest) {

    var response = signupUseCase.execute(signupRequest);

    if (response.getData() == null) {
      return ResponseEntity.badRequest().body(response);
    }

    return ResponseEntity.created(URI.create("")).body(response);
  }
}
