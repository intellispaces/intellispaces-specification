package tech.intellispaces.specification.space.exception;

import tech.intellispaces.commons.exception.CheckedException;

public class SpecificationException extends CheckedException {

  public SpecificationException(String message) {
    super(message);
  }

  public SpecificationException(String message, Exception cause) {
    super(message, cause);
  }
}
