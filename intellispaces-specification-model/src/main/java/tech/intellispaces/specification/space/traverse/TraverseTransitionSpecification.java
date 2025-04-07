package tech.intellispaces.specification.space.traverse;

/**
 * The traverse transition specification.
 */
public interface TraverseTransitionSpecification {

  boolean isToTransition();

  boolean isThruTransition();

  TraverseTransitionToSpecification asToTransition();

  TraverseTransitionThruSpecification asThruTransition();
}
