package tech.intellispaces.core.specification.space;

import tech.intellispaces.core.specification.space.constraint.ConstraintSpecification;
import tech.intellispaces.core.specification.space.reference.SpaceReference;

import java.util.List;

/**
 * The super domain specification.
 */
public interface SuperDomainSpecification {

  SpaceReference reference();

  List<ConstraintSpecification> constraints();
}
