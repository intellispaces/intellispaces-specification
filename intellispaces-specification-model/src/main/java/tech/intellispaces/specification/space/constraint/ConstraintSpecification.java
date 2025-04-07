package tech.intellispaces.specification.space.constraint;

/**
 * The constraint specification.
 */
public interface ConstraintSpecification {

  ConstraintType type();

  boolean isEquivalenceConstraint();

  EquivalenceConstraintSpecification asEquivalenceConstraint();
}
