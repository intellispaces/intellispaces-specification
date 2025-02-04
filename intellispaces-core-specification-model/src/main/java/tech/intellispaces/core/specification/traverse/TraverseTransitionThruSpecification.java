package tech.intellispaces.core.specification.traverse;

import tech.intellispaces.core.specification.reference.Reference;

/**
 * The traverse transition "THRU channel" specification.
 */
public interface TraverseTransitionThruSpecification extends TraverseTransitionSpecification {

  Reference channel();
}
