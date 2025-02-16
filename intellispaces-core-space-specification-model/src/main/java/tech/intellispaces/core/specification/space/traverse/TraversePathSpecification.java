package tech.intellispaces.core.specification.space.traverse;

import tech.intellispaces.core.specification.space.reference.SpaceReference;

import java.util.List;

/**
 * The traverse path specification.
 */
public interface TraversePathSpecification {

  SpaceReference sourceDomain();

  List<TraverseTransitionSpecification> transitions();
}
