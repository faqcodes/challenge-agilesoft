package com.faqcodes.agilesoft.users.models;

import java.util.List;

import com.faqcodes.agilesoft.tasks.domain.TaskEntity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GetResponse {
  private String username;
  private String password;
  private String name;
  private List<TaskEntity> tasks;
}