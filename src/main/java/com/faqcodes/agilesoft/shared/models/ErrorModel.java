package com.faqcodes.agilesoft.shared.models;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ErrorModel {
  private final String code;
  private final String detail;
}
