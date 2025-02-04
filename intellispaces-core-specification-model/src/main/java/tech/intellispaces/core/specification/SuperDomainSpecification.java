package tech.intellispaces.core.specification;

import tech.intellispaces.core.specification.constraint.ConstraintSpecification;
import tech.intellispaces.core.specification.reference.Reference;

import java.util.List;

/**
 * The super domain specification.
 */
public interface SuperDomainSpecification {

  Reference domain();

  List<ConstraintSpecification> constraints();
}
