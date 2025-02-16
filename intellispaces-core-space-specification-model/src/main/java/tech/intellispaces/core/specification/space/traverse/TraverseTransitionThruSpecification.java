package tech.intellispaces.core.specification.space.traverse;

import tech.intellispaces.core.specification.space.reference.SpaceReference;

/**
 * The traverse transition "THRU channel" specification.
 */
public interface TraverseTransitionThruSpecification extends TraverseTransitionSpecification {

  SpaceReference channel();
}
