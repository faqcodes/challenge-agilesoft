package com.faqcodes.agilesoft.users.models;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SignupRequest {
  @NotBlank(message = "Debe ingresar su nombre")
  private final String name;

  @NotBlank(message = "Debe ingresar un nombre de usuario")
  private final String username;

  @NotBlank(message = "Debe ingresar una contraseña")
  private final String password;
}