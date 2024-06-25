package com.faqcodes.agilesoft.tasks.models;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GetTaskRequest {
  private String username;
}
