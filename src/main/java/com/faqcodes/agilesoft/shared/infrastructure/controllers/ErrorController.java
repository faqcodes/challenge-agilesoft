package com.faqcodes.agilesoft.shared.infrastructure.controllers;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import com.faqcodes.agilesoft.shared.models.ErrorModel;
import com.faqcodes.agilesoft.shared.models.MessageResult;

@ControllerAdvice
public class ErrorController {

  private static final Logger logger = LoggerFactory.getLogger(ErrorController.class);

  @ExceptionHandler(AccessDeniedException.class)
  public ResponseEntity<String> handleAccessDeniedException(AccessDeniedException ex) {
    return new ResponseEntity<>("Access Denied (403)", HttpStatus.FORBIDDEN);
  }

  // Error MethodArgumentNotValid
  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<?> handleValidationExceptions(MethodArgumentNotValidException e) {
    var errors = new ArrayList<ErrorModel>();

    e.getBindingResult().getAllErrors().forEach(error -> {
      var errorMessage = error.getDefaultMessage();

      var errorModel = new ErrorModel(
          "0",
          errorMessage);

      errors.add(errorModel);
    });

    var errorResponse = new MessageResult<>("ERROR", "Error en los parámetros ingresados", errors, null);

    return ResponseEntity.badRequest().body(errorResponse);
  }

  // Error general
  @ExceptionHandler(Exception.class)
  public ResponseEntity<?> handleException(Exception e) {
    var errors = new ArrayList<ErrorModel>();
    logger.info("Error general");
    errors.add(
        new ErrorModel(
            "400",
            e.getMessage()));

    var errorResponse = new MessageResult<>("ERROR", e.getMessage(), null, null);

    return ResponseEntity.badRequest().body(errorResponse);
  }
}
