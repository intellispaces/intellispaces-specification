package tech.intellispaces.core.specification.space.constraint;

/**
 * The constraint specification.
 */
public interface ConstraintSpecification {

  ConstraintType type();

  boolean isEquivalenceConstraint();

  EquivalenceConstraintSpecification asEquivalenceConstraint();
}
