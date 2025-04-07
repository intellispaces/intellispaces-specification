package tech.intellispaces.specification.space;

import tech.intellispaces.specification.space.constraint.ConstraintSpecification;
import tech.intellispaces.specification.space.reference.SpaceReference;

import java.util.List;

/**
 * The super domain specification.
 */
public interface SuperDomainSpecification {

  SpaceReference reference();

  List<ConstraintSpecification> constraints();
}
