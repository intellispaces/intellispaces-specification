package tech.intellispaces.core.specification.constraint;

/**
 * The constraint specification.
 */
public interface ConstraintSpecification {

  ConstraintType type();

  boolean isEquivalenceConstraint();

  EquivalenceConstraintSpecification asEqualConstraint();
}
