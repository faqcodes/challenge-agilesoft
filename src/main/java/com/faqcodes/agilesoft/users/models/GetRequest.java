package com.faqcodes.agilesoft.users.models;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GetRequest {
  private String username;
}