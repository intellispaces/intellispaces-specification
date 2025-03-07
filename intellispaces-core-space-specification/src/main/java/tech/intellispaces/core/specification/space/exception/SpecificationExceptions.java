package tech.intellispaces.core.specification.space.exception;

import tech.intellispaces.commons.text.StringFunctions;

public interface SpecificationExceptions {

  static SpecificationException withMessage(String message) {
    return new SpecificationException(message);
  }

  static SpecificationException withMessage(String template, Object... params) {
    return new SpecificationException(StringFunctions.resolveTemplate(template, params));
  }

  static SpecificationException withCauseAndMessage(Exception cause, String message) {
    return new SpecificationException(message, cause);
  }

  static SpecificationException withCauseAndMessage(
      Exception cause, String template, Object... params
  ) {
    return new SpecificationException(StringFunctions.resolveTemplate(template, params), cause);
  }
}
