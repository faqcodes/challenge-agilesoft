package com.faqcodes.agilesoft.users.models;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Builder
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class SigninRequest {
  @NotBlank(message = "Debe ingresar un nombre de usuario")
  private final String username;

  @NotBlank(message = "Debe ingresar una contraseña")
  private final String password;
}
