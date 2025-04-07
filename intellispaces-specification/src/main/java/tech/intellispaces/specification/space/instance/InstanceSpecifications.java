package tech.intellispaces.specification.space.instance;

import tech.intellispaces.specification.space.reference.SpaceReference;

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
