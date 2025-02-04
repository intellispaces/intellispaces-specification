package tech.intellispaces.core.specification.constraint;

import tech.intellispaces.core.specification.instance.InstanceSpecification;
import tech.intellispaces.core.specification.traverse.TraversePathSpecification;

import java.util.List;

/**
 * The equivalence constraint specification.
 */
public interface EquivalenceConstraintSpecification extends ConstraintSpecification {

  List<TraversePathSpecification> traversePaths();

  InstanceSpecification target();
}
