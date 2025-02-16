package tech.intellispaces.core.specification.space.instance;

import tech.intellispaces.core.specification.space.reference.SpaceReference;

public interface InstanceSpecifications {

  static InstanceSpecification get(String string) {
    return new InstanceSpecificationImpl(string);
  }

  static InstanceSpecification get(SpaceReference reference) {
    return new InstanceSpecificationImpl(reference);
  }

  static InstanceSpecification get(CustomInstanceSpecification customInstance) {
    return new InstanceSpecificationImpl(customInstance);
  }
}
