package com.faqcodes.agilesoft.tasks.models;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CreateResponse {
  private final String taskId;
  private final String name;
  private final String description;
  private final String createdAt;
  private final String updatedAt;
  private final boolean status;
}
