package tech.intellispaces.core.specification;

import tech.intellispaces.core.specification.constraint.ConstraintSpecification;
import tech.intellispaces.core.specification.reference.SpaceReference;

import java.util.List;

/**
 * The super domain specification.
 */
public interface SuperDomainSpecification {

  SpaceReference reference();

  List<ConstraintSpecification> constraints();
}
