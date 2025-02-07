package tech.intellispaces.core.specification;

import tech.intellispaces.core.specification.constraint.ConstraintSpecification;
import tech.intellispaces.core.specification.instance.InstanceSpecification;
import tech.intellispaces.core.specification.reference.SpaceReference;

import java.util.List;

record ContextChannelSpecificationImpl(
    String alias,
    String cid,
    String name,
    String description,
    List<ContextChannelSpecification> projections,
    SpaceReference targetDomain,
    DomainBoundSpecification targetDomainBounds,
    String targetAlias,
    InstanceSpecification targetInstance,
    List<ConstraintSpecification> targetConstraints,
    ImmobilityType targetImmobilityType,
    List<String> allowedTraverse
) implements ContextChannelSpecification {
}
