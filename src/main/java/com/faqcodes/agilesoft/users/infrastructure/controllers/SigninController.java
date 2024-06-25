package com.faqcodes.agilesoft.users.infrastructure.controllers;

import java.net.URI;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.faqcodes.agilesoft.shared.application.UseCase;
import com.faqcodes.agilesoft.users.models.SigninRequest;
import com.faqcodes.agilesoft.users.models.SigninResponse;

import jakarta.validation.Valid;

@Validated
@RestController
// @CrossOrigin(origins = "*")
@RequestMapping("/api/users")
public class SigninController {

  UseCase<SigninRequest, SigninResponse> signinUseCase;

  public SigninController(UseCase<SigninRequest, SigninResponse> signinUseCase) {
    this.signinUseCase = signinUseCase;
  }

  @PostMapping("/signin")
  public ResponseEntity<?> signin(@Valid @RequestBody SigninRequest signinRequest) {

    var response = signinUseCase.execute(signinRequest);

    if (response.getCode().equals("ERROR")) {
      return ResponseEntity.badRequest().body(response);
    }

    return ResponseEntity.created(URI.create("")).body(response);
  }
}
