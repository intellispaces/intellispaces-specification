package tech.intellispaces.core.specification.traverse;

/**
 * The traverse transition specification.
 */
public interface TraverseTransitionSpecification {

  boolean isToTransition();

  boolean isThruTransition();

  TraverseTransitionToSpecification asToTransition();

  TraverseTransitionThruSpecification asThruTransition();
}
