package com.faqcodes.agilesoft.tasks.models;

import lombok.Builder;
import lombok.Data;

import java.util.List;

import com.faqcodes.agilesoft.tasks.domain.TaskEntity;

@Data
@Builder
public class GetTaskResponse {
  private final List<TaskEntity> tasks;
}
