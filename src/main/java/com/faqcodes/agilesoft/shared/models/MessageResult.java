package com.faqcodes.agilesoft.shared.models;

import java.util.ArrayList;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Builder
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class MessageResult<T> {
  private final String code;
  private final String message;
  private final ArrayList<ErrorModel> errors;
  private final T data;
}
