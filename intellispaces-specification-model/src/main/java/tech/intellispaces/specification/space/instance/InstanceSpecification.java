package tech.intellispaces.specification.space.instance;

import tech.intellispaces.specification.space.reference.SpaceReference;

/**
 * The instance specification.
 */
public interface InstanceSpecification {

  InstanceType type();

  boolean isString();

  boolean isReference();

  boolean isCustomInstance();

  String asString();

  SpaceReference asReference();

  CustomInstanceSpecification asCustomInstance();
}
