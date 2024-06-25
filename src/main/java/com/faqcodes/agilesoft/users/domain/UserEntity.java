package com.faqcodes.agilesoft.users.domain;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UserEntity {
  private final String id;
  private final String name;
  private final String username;
}
