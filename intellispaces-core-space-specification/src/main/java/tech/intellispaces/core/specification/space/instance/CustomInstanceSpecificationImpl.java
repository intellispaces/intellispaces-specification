package tech.intellispaces.core.specification.space.instance;

import tech.intellispaces.core.specification.space.constraint.ConstraintSpecification;
import tech.intellispaces.core.specification.space.reference.SpaceReference;

import java.util.List;
import java.util.Map;

record CustomInstanceSpecificationImpl(
    SpaceReference domain,
    Map<String, InstanceSpecification> projections,
    List<ConstraintSpecification> constraints
) implements CustomInstanceSpecification {
}
