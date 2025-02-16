package tech.intellispaces.core.specification.space.constraint;

import tech.intellispaces.core.specification.space.instance.InstanceSpecification;
import tech.intellispaces.core.specification.space.traverse.TraversePathSpecification;

import java.util.List;

public interface EquivalenceConstraintSpecifications {

  static EquivalenceConstraintSpecification get(
      List<TraversePathSpecification> traversePaths, InstanceSpecification instance
  ) {
    return new EquivalenceConstraintSpecificationImpl(traversePaths, instance);
  }
}
