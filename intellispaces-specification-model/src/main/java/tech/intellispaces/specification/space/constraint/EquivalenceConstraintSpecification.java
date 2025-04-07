package tech.intellispaces.specification.space.constraint;

import tech.intellispaces.specification.space.instance.InstanceSpecification;
import tech.intellispaces.specification.space.traverse.TraversePathSpecification;

import java.util.List;

/**
 * The equivalence constraint specification.
 */
public interface EquivalenceConstraintSpecification extends ConstraintSpecification {

  List<TraversePathSpecification> traversePaths();

  InstanceSpecification instance();
}
