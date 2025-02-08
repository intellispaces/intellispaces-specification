package tech.intellispaces.core.specification.instance;

import tech.intellispaces.core.specification.constraint.ConstraintSpecification;
import tech.intellispaces.core.specification.reference.SpaceReference;

import java.util.List;
import java.util.Map;

record CustomInstanceSpecificationImpl(
    SpaceReference domain,
    Map<String, InstanceSpecification> projections,
    List<ConstraintSpecification> constraints
) implements CustomInstanceSpecification {
}
