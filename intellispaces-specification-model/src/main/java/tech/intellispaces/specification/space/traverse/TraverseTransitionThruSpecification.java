package tech.intellispaces.specification.space.traverse;

import tech.intellispaces.specification.space.reference.SpaceReference;

/**
 * The traverse transition "THRU channel" specification.
 */
public interface TraverseTransitionThruSpecification extends TraverseTransitionSpecification {

  SpaceReference channel();
}
