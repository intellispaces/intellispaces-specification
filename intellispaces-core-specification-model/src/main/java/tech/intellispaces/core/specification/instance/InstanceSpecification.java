package tech.intellispaces.core.specification.instance;

import tech.intellispaces.core.specification.reference.Reference;

/**
 * The instance specification.
 */
public interface InstanceSpecification {

  InstanceType type();

  boolean isString();

  boolean isReference();

  String asString();

  Reference asReference();
}
