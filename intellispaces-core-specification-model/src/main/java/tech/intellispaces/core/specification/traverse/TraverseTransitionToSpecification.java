package tech.intellispaces.core.specification.traverse;

import tech.intellispaces.core.specification.reference.Reference;

/**
 * The traverse transition "TO domain" specification.
 */
public interface TraverseTransitionToSpecification extends TraverseTransitionSpecification {

  Reference domain();

  boolean isSuperDomain();
}
