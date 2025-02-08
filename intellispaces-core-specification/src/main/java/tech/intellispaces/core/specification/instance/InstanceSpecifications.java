package tech.intellispaces.core.specification.instance;

import tech.intellispaces.core.specification.reference.SpaceReference;

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
