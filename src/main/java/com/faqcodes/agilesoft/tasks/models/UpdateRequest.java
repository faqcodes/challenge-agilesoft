package com.faqcodes.agilesoft.tasks.models;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UpdateRequest {
  @NotBlank(message = "Debe ingresar el ID de la tarea")
  private final String taskId;

  @NotNull(message = "Debe ingresar el estado")
  private final boolean status;
}
