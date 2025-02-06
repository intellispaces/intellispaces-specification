package tech.intellispaces.core.specification.traverse;

import tech.intellispaces.core.specification.reference.SpaceReference;

import java.util.List;

/**
 * The traverse path specification.
 */
public interface TraversePathSpecification {

  SpaceReference sourceDomain();

  List<TraverseTransitionSpecification> transitions();
}
