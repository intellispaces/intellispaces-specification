package tech.intellispaces.core.specification.space.exception;

import tech.intellispaces.commons.base.exception.CheckedException;

public class SpecificationException extends CheckedException {

  public SpecificationException(String message) {
    super(message);
  }

  public SpecificationException(String message, Exception cause) {
    super(message, cause);
  }
}
