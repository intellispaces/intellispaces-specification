package tech.intellispaces.core.specification.constraint;

import tech.intellispaces.core.specification.traverse.TraversePathSpecification;

import java.util.List;

public interface EquivalenceConstraintSpecifications {

  static EquivalenceConstraintSpecification get(List<TraversePathSpecification> traversePaths) {
    return new EquivalenceConstraintSpecificationImpl(traversePaths, null);
  }
}
