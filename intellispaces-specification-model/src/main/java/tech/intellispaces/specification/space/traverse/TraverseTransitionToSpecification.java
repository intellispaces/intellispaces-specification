package tech.intellispaces.specification.space.traverse;

import tech.intellispaces.specification.space.reference.SpaceReference;

/**
 * The traverse transition "TO domain" specification.
 */
public interface TraverseTransitionToSpecification extends TraverseTransitionSpecification {

  SpaceReference domain();

  boolean isSuperDomain();
}
