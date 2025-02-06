package tech.intellispaces.core.specification.traverse;

import tech.intellispaces.core.specification.reference.SpaceReference;

/**
 * The traverse transition "TO domain" specification.
 */
public interface TraverseTransitionToSpecification extends TraverseTransitionSpecification {

  SpaceReference domain();

  boolean isSuperDomain();
}
