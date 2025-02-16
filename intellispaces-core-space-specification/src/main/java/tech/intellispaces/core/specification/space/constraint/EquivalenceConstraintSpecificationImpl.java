package tech.intellispaces.core.specification.space.constraint;

import tech.intellispaces.core.specification.space.instance.InstanceSpecification;
import tech.intellispaces.core.specification.space.traverse.TraversePathSpecification;

import java.util.List;

record EquivalenceConstraintSpecificationImpl(
  List<TraversePathSpecification> traversePaths,
  InstanceSpecification instance
) implements EquivalenceConstraintSpecification {

  @Override
  public ConstraintType type() {
    return ConstraintTypes.Equivalence;
  }

  @Override
  public boolean isEquivalenceConstraint() {
    return true;
  }

  @Override
  public EquivalenceConstraintSpecification asEquivalenceConstraint() {
    return this;
  }
}
