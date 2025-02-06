package tech.intellispaces.core.specification.instance;

import tech.intellispaces.core.specification.reference.SpaceReference;

/**
 * The instance specification.
 */
public interface InstanceSpecification {

  InstanceType type();

  boolean isString();

  boolean isReference();

  String asString();

  SpaceReference asReference();
}
