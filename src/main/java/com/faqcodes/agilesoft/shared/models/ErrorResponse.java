package com.faqcodes.agilesoft.shared.models;

import java.util.ArrayList;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ErrorResponse {
  private final ArrayList<ErrorModel> error;
}
