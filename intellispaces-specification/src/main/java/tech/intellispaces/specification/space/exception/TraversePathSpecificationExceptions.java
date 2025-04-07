package tech.intellispaces.specification.space.exception;

import tech.intellispaces.commons.text.StringFunctions;

public interface TraversePathSpecificationExceptions {

  static TraversePathSpecificationException withMessage(String message) {
    return new TraversePathSpecificationException(message);
  }

  static TraversePathSpecificationException withMessage(String template, Object... params) {
    return new TraversePathSpecificationException(StringFunctions.resolveTemplate(template, params));
  }

  static TraversePathSpecificationException withCauseAndMessage(Exception cause, String message) {
    return new TraversePathSpecificationException(message, cause);
  }

  static TraversePathSpecificationException withCauseAndMessage(
      Exception cause, String template, Object... params
  ) {
    return new TraversePathSpecificationException(StringFunctions.resolveTemplate(template, params), cause);
  }
}
