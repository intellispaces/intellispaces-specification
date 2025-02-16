package tech.intellispaces.core.specification.space.traverse;

/**
 * The traverse transition specification.
 */
public interface TraverseTransitionSpecification {

  boolean isToTransition();

  boolean isThruTransition();

  TraverseTransitionToSpecification asToTransition();

  TraverseTransitionThruSpecification asThruTransition();
}
