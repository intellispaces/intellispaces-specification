package tech.intellispaces.core.specification;

import tech.intellispaces.core.specification.constraint.ConstraintSpecification;
import tech.intellispaces.core.specification.reference.SpaceReference;

import java.util.List;

record SuperDomainSpecificationImpl(
    SpaceReference domain,
    List<ConstraintSpecification> constraints
) implements SuperDomainSpecification {
}
