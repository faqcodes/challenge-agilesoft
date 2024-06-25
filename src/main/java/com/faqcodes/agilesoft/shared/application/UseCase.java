package com.faqcodes.agilesoft.shared.application;

import com.faqcodes.agilesoft.shared.models.MessageResult;

public interface UseCase<T, U> {
  MessageResult<U> execute(T requestModel);
}