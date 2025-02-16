package tech.intellispaces.core.specification.space.traverse;

import tech.intellispaces.core.specification.space.reference.SpaceReference;

/**
 * The traverse transition "TO domain" specification.
 */
public interface TraverseTransitionToSpecification extends TraverseTransitionSpecification {

  SpaceReference domain();

  boolean isSuperDomain();
}
