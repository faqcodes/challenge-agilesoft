package com.faqcodes.agilesoft.tasks.models;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CreateRequest {
  @NotBlank(message = "Debe ingresar un nombre")
  private final String name;

  @NotBlank(message = "Debe ingresar una descripcion")
  private final String description;

  private String username;
}
