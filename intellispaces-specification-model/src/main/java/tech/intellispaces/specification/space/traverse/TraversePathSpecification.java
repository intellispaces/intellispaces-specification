package tech.intellispaces.specification.space.traverse;

import tech.intellispaces.specification.space.reference.SpaceReference;

import java.util.List;

/**
 * The traverse path specification.
 */
public interface TraversePathSpecification {

  SpaceReference sourceDomain();

  List<TraverseTransitionSpecification> transitions();
}
