package tech.intellispaces.specification.space;

import tech.intellispaces.specification.space.constraint.ConstraintSpecification;
import tech.intellispaces.specification.space.reference.SpaceReference;

import java.util.List;

record SuperDomainSpecificationImpl(
    SpaceReference reference,
    List<ConstraintSpecification> constraints
) implements SuperDomainSpecification {
}
