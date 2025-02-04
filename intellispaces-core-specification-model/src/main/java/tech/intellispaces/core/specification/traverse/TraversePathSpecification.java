package tech.intellispaces.core.specification.traverse;

import tech.intellispaces.core.specification.reference.Reference;

import java.util.List;

/**
 * The traverse path specification.
 */
public interface TraversePathSpecification {

  Reference sourceDomain();

  List<TraverseTransitionSpecification> transitions();
}
